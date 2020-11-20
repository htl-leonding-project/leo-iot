import tensorflow as tf
import tensorflow_docs as tfdocs
import tensorflow_docs.modeling
import numpy as np
import requests
import json
import os

def get_metrics():
    return [
        tf.keras.metrics.TruePositives(name='tp'),
        tf.keras.metrics.FalsePositives(name='fp'),
        tf.keras.metrics.TrueNegatives(name='tn'),
        tf.keras.metrics.FalseNegatives(name='fn'), 
        tf.keras.metrics.BinaryAccuracy(name='accuracy'),
        tf.keras.metrics.Precision(name='precision'),
        tf.keras.metrics.Recall(name='recall'),
        tf.keras.metrics.AUC(name='auc')
    ]

def get_callbacks(patience):
    return [
        tfdocs.modeling.EpochDots(20),
        tf.keras.callbacks.EarlyStopping(
            monitor='val_recall', 
            verbose=1,
            patience=patience,
            mode='max',
            restore_best_weights=True)
    ]

def get_optimizer(learning_rate, decay):
    #lr_schedule = tf.keras.optimizers.schedules.InverseTimeDecay(
    #    0.001,
    #    decay_steps=STEPS_PER_EPOCH*1000,
    #    decay_rate=1,
    #    staircase=False)
    return tf.keras.optimizers.Adam(lr=learning_rate, decay=decay)#lr_schedule)
    #return tf.keras.optimizers.SGD(lr=learning_rate, decay=decay)

def get_sensor_names():
    return [
        'co2Indoor',
        'humidityIndoor',
        'humidityOutdoor',
        'temperatureIndoor',
        'temperatureOutdoor'
    ]

def get_data(time_steps, sensors, num_datapoints=None):
    if num_datapoints == None:
        resp = requests.get('http://vm05.htl-leonding.ac.at/data/all')
    else:
        resp = requests.get('http://vm05.htl-leonding.ac.at/data/all?max={}'.format(num_datapoints))
    if resp.status_code != 200:
        raise ApiError('GET /data/all {}'.format(resp.status_code))
    size = len(resp.json()['object'])
    arr = np.empty([size, 5])
    y = np.empty(size)
    index = 0
    for item in resp.json()['object']:
        if all([sensor in item for sensor in sensors]) and 'windowOpenCount' in item:
            arr[index] = np.array([item[sensor] for sensor in sensors])
            y[index] = 1 if item['windowOpenCount'] > 0 else 0
            if not np.isnan(arr[index]).any():
                index += 1
    arr = arr[:index]
    y = y[:index]
    size = arr.shape[0]
    x = np.empty([size - time_steps + 1, time_steps, 5])
    for i in range(time_steps, size + 1):
        x[i - time_steps] = arr[i - time_steps:i]
    return (x, y[time_steps-1:])

def scale_data(X):
    min_X = X.min(axis=1).min(axis=0).reshape((1, 1, -1))
    max_X = X.max(axis=1).max(axis=0).reshape((1, 1, -1))
    nom = (X-min_X)*2
    denom = max_X - min_X
    denom[denom==0] = 1
    return (-1 + nom/denom, min_X, max_X)

def split_data(X, Y, test_fraction, validation_fraction, splits=3):
    #split = int(Y.shape[0] * (1. - fraction))
    #return (X[:split], Y[:split], X[split:], Y[split:])
    train = np.zeros(Y.shape[0], dtype=bool)
    test = np.zeros(Y.shape[0], dtype=bool)
    validation = np.zeros(Y.shape[0], dtype=bool)
    split_size = int(Y.shape[0] / (2 * splits))
    split_rest = Y.shape[0] - split_size
    count = 0
    for _ in range(splits):
        act_split_size = split_size
        if split_rest > 0:
            act_split_size += 1
            split_rest -= 1
        train_size = int(act_split_size * (1.0 - test_fraction - validation_fraction))
        train[count : count + train_size] = True
        count += train_size
        test_size = int(act_split_size * test_fraction)
        test[count : count + test_size] = True
        count += test_size
        validation_size = act_split_size - train_size - test_size
        validation[count : count + validation_size] = True
        count += validation_size
    for _ in range(splits):
        act_split_size = split_size
        if split_rest > 0:
            act_split_size += 1
            split_rest -= 1
        validation_size = int(act_split_size * validation_fraction)
        validation[count : count + validation_size] = True
        count += validation_size
        test_size = int(act_split_size * test_fraction)
        test[count : count + test_size] = True
        count += test_size
        train_size = act_split_size - validation_size - test_size
        train[count : count + train_size] = True
        count += train_size
    return X[train], Y[train], X[validation], Y[validation], X[test], Y[test]

def train_models(model_dict, train, validation, learning_rate, decay, max_epochs, batch_size, patience, class_weight=None):
    metrics = get_metrics()
    callbacks = get_callbacks(patience)
    optimizer = get_optimizer(learning_rate, decay)
    
    histories = {}
    
    for (name, model) in model_dict.items():
        model.compile(optimizer=optimizer,
                loss='binary_crossentropy',
                metrics=metrics,
                weighted_metrics=[tf.keras.metrics.BinaryCrossentropy(name='binary_crossentropy')])
        
        model.summary()

        histories[name] = model.fit(
            train[0],
            train[1],
            epochs=max_epochs,
            batch_size=batch_size,
            validation_data=validation,
            callbacks=callbacks,
            class_weight=class_weight,
            verbose=0)
    
    return histories

def save_models(model_dict, time_steps, min_x, max_x, batch_size, export_path):
    config = { 'batch_size': batch_size, 'time_steps': time_steps, 
               'min_x': min_x.tolist(), 'max_x': max_x.tolist() }
    os.makedirs(export_path)
    for name, model in model_dict.items():
        os.mkdir(os.path.join(export_path, name))
        model.save(os.path.join(export_path, name, 'model.h5'))
        with open(os.path.join(export_path, name, 'config.json'), 'w') as outfile:
            json.dump(config, outfile)
