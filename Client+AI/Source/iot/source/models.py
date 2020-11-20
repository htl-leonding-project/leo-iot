import tensorflow as tf
from tensorflow.keras import layers

def get_model_dict(num_input, num_time_steps, initial_bias=None):
    if initial_bias is not None:
        initial_bias = tf.keras.initializers.Constant(initial_bias)
    else:
        initial_bias = 'zeros'
    
    smallLSTM = tf.keras.Sequential([
        layers.LSTM(50, kernel_regularizer=tf.keras.regularizers.l2(0.0001), input_shape=(num_time_steps, num_input)),
        layers.Dropout(0.3),
        layers.Dense(20, kernel_regularizer=tf.keras.regularizers.l2(0.0001), activation='relu'),
        layers.Dropout(0.3),
        layers.Dense(1, activation='sigmoid')
    ])
    
    smallGRU = tf.keras.Sequential([
        layers.GRU(100, kernel_regularizer=tf.keras.regularizers.l2(0.0001), input_shape=(num_time_steps, num_input)),
        layers.Dropout(0.3),
        layers.Dense(20, activation='relu'),
        layers.Dropout(0.3),
        layers.Dense(1, activation='sigmoid')
    ])
    
    longLSTM = tf.keras.Sequential([
        layers.LSTM(100, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True, input_shape=(num_time_steps, num_input)),
        layers.Dropout(0.3),
        layers.LSTM(80, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True),
        layers.Dropout(0.3),
        layers.LSTM(50, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True),
        layers.Dropout(0.3),
        layers.LSTM(50, kernel_regularizer=tf.keras.regularizers.l2(0.0001)),
        layers.Dropout(0.3),
        layers.Dense(20, kernel_regularizer=tf.keras.regularizers.l2(0.0001), activation='relu'),
        layers.Dropout(0.3),
        layers.Dense(1, activation='sigmoid')
    ])
    
    longGRU = tf.keras.Sequential([
        layers.GRU(200, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True, input_shape=(num_time_steps, num_input)),
        layers.Dropout(0.3),
        layers.GRU(150, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True),
        layers.Dropout(0.3),
        layers.GRU(100, kernel_regularizer=tf.keras.regularizers.l2(0.0001), return_sequences=True),
        layers.Dropout(0.3),
        layers.GRU(100, kernel_regularizer=tf.keras.regularizers.l2(0.0001)),
        layers.Dropout(0.3),
        layers.Dense(20, kernel_regularizer=tf.keras.regularizers.l2(0.0001), activation='relu'),
        layers.Dropout(0.3),
        layers.Dense(1, activation='sigmoid')
    ])
    
    return {
        "smallLSTM": smallLSTM,
        "smallGRU": smallGRU,
        "longLSTM": longLSTM,
        "longGRU": longGRU
    }
