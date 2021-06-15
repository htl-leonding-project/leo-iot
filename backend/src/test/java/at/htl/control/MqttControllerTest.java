package at.htl.control;

import at.htl.config.MqttConfiguration;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
public class MqttControllerTest {

    @Inject
    MqttController controller;

    @Inject
    MqttConfiguration configuration;

    @Rule
    private static final GenericContainer mosquitto = new GenericContainer<>("eclipse-mosquitto:1.4.12")
            .withExposedPorts(1883, 9001);

    @Test
    public void getClient() {
        MqttClient client = controller.getClient();

        assertThat(client)
                .isNotNull()
                .isInstanceOf(MqttClient.class);
    }

    @Test
    public void subscribeWithoutParse() throws
            MqttException,
            ExecutionException,
            InterruptedException,
            TimeoutException
    {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        controller.subscribe("test", (topic, message) -> future.complete(new ArrayList<>() {{
            add(topic);
            add(new String(message.getPayload()));
        }}));

        controller.getClient().publish("test", new MqttMessage(
                "test_value".getBytes(StandardCharsets.UTF_8)
        ));

        assertThat(future.get(1, TimeUnit.SECONDS).get(0))
                .isEqualTo("test");

        assertThat(future.get(1, TimeUnit.SECONDS).get(1))
                .isEqualTo("test_value");
    }

}
