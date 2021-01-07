FROM tensorflow/tensorflow:2.0.1-gpu-py3
ADD ./mqtt.py /mqtt.py

RUN pip3 install numpy requests paho-mqtt
CMD ["python", "mqtt.py"]
