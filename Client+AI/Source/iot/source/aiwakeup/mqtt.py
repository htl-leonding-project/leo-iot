import time
import paho.mqtt.client as mqtt

mqtt_client_name = "ai_wake_up"
mqtt_host_name = "iotserverbroker"
mqtt_client_username = "ui_client"
mqtt_client_password = "ud84jc23"
mqtt_wake_up_topic = "htlleonding/predict_windows"

wake_up_period = 60 * 5 #Seconds

def on_connect(client, userdata, flags, rc):
  print("Connected with result code "+str(rc))

print('Creating Mqtt Client')

client = mqtt.Client(mqtt_client_name, )
client.username_pw_set(mqtt_client_username, password=mqtt_client_password)

client.on_connect = on_connect

print('Connecting to {}'.format(mqtt_host_name))

client.connect(mqtt_host_name)

client.loop_start()

while True:
  client.publish(mqtt_wake_up_topic)
  time.sleep(wake_up_period)
