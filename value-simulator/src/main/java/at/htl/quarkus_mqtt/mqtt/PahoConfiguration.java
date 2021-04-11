package at.htl.quarkus_mqtt.mqtt;

import io.quarkus.arc.config.ConfigProperties;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;

// will take every configuration with prefix paho because of the name PahoConfiguration
@ConfigProperties
public class PahoConfiguration {
    // URL to the Broker
    public String url = "tcp://vm90.htl-leonding.ac.at:1883";
    // topic to subscribe
    public String topicRead = "/";
    // topic to publish
    public String topicWrite = "simulated-rooms/";
    // username for the broker, leave empty if no username is needed
    public String username = "student";
    // password for the broker, leave empty if no password is needed
    public String password = "passme";
    // Quality Of Service
    public int qos = 1;
}
