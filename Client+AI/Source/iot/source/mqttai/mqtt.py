import tensorflow as tf
import requests
import numpy as np
import paho.mqtt.client as mqtt
import os
import json
from datetime import datetime

sensors = ['co2Indoor', 'humidityIndoor', 'humidityOutdoor', 'temperatureIndoor', 'temperatureOutdoor']

model_path = '/model'

mqtt_client_name = "window_predictor"
mqtt_host_name = "iotserverbroker"
mqtt_client_username = "ui_client"
mqtt_client_password = "ud84jc23"
mqtt_wake_up_topic = "htlleonding/predict_windows"

window_open_5ahif = "htlleonding/firstfloor/e581/pc/window/state"

print('Loading Model')

model = tf.keras.models.load_model(os.path.join(model_path, 'model.h5'))

with open(os.path.join(model_path, 'config.json')) as json_data_file:
    data = json.load(json_data_file)
batch_size = int(data['batch_size'])
time_steps = int(data['time_steps'])
min_x = np.array(data['min_x'])
max_x = np.array(data['max_x'])

def make_predictions(data):
    return (model.predict(data, batch_size=batch_size) > 0.5).astype("int32").flatten()

def scale_data(x):
    nom = (x-min_x)*2
    denom = max_x - min_x
    denom[denom==0] = 1
    return -1 + nom/denom

def get_data():
    resp = requests.get('http://vm05.htl-leonding.ac.at/data/all?max={}'.format(time_steps))
    if resp.status_code != 200:
        raise ApiError('GET /data/all?max={} {}'.format(time_steps, resp.status_code))
    x = np.empty([time_steps, 5])
    for i, item in enumerate(resp.json()['object']):
        if not all([sensor in item for sensor in sensors]):
            return np.array([])
        x[i] = np.array([item[sensor] for sensor in sensors])
    return x[np.newaxis, :]

def publish_predictions(y, client):
    payload = {
        "timestamp": int(datetime.timestamp(datetime.now())),
        "value": np.where(y, 'Open', 'Closed')[0]
    }
    client.publish(window_open_5ahif, json.dumps(payload), qos=1, retain=True) 

def on_connect(client, userdata, flags, rc):
    print("Connected with result code "+str(rc))
    print('Listening to {}'.format(mqtt_wake_up_topic))
    client.subscribe(mqtt_wake_up_topic)

def on_message(client, userdata, message):
    x = get_data()
    if len(x.shape) == 3:
        x = scale_data(x)
        y = make_predictions(x)
        publish_predictions(y, client)
    else:
        print('Wrong data format')

print('Creating Mqtt Client')

client = mqtt.Client(mqtt_client_name)
client.username_pw_set(mqtt_client_username, password=mqtt_client_password)

client.on_connect = on_connect
client.on_message = on_message

print('Connecting to {}'.format(mqtt_host_name))

client.connect(mqtt_host_name)

print('Entering Loop_forever')

client.loop_forever()
