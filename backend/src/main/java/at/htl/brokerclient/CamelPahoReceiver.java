package at.htl.brokerclient;

import at.htl.controller.MeasurementRepository;
import at.htl.entity.Measurement;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;

public class CamelPahoReceiver implements MqttCallback {
    @Inject
    MeasurementRepository measurementRepository;

    @ConfigProperty(name = "mqtt.url")
    String mqttUrl;

    @ConfigProperty(name = "mqtt.topic")
    String mqttTopic;

    @ConfigProperty(name = "mqtt.username")
    String mqttUsername;

    @ConfigProperty(name = "MQTT_PASSWORD")
    Optional<String> mqttPassword;

    @ConfigProperty(name = "mqtt.qos", defaultValue = "1")
    int mqttQos;

    void onStart(@Observes StartupEvent ev) {
        try {
            MqttAsyncClient client = new MqttAsyncClient(
                    mqttUrl,
                    MqttAsyncClient.generateClientId(),
                    new MemoryPersistence()
            );

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(mqttUsername);

            connOpts.setPassword(mqttPassword.orElseThrow().toCharArray());
            client.connect(connOpts).waitForCompletion();
            client.subscribe(mqttTopic, mqttQos);
            client.setCallback(this);

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void connectionLost(Throwable throwable) { }

    @Override
    public void messageArrived(String topic, MqttMessage msg) {
        JsonObject jsonObject = JsonbBuilder.create().fromJson(new String(msg.getPayload()), JsonObject.class);

        Measurement measurement = new Measurement(
                LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(jsonObject.getInt("timestamp")),
                        TimeZone.getDefault().toZoneId()
                ),
                topic.split("/")[2],
                jsonObject.getInt("value"),
                topic.split("/")[1]
        );

        measurementRepository.save(measurement);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) { }
}
