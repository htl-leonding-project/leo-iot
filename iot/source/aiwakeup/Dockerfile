FROM python:3.6-alpine
ADD mqtt.py /mqtt.py

RUN pip3 install paho-mqtt
CMD ["python", "mqtt.py"]
