package at.htl.config;

import io.quarkus.arc.config.ConfigProperties;

import java.util.Optional;

/**
 * This is the configuration of the mqtt broker. Configure them not her use a .env file or the application.properties.
 * Find more here https://quarkus.io/guides/config-reference
 * @author QuirinEcker
 */
@ConfigProperties
public class MqttConfiguration {

    /**
     * Url of the broker
     */
    public String url = "localhost";

    /**
     * protocol for the broker (tcp, ws, http ...)
     */
    public String protocol = "tcp";

    /**
     * port of the broker
     */
    public int port = 1883;

    /**
     * password credential for the broker
     */
    public Optional<String> password;

    /**
     * username credential for the broker
     */
    public Optional<String> username;

    /**
     * This is a help function for the getting the url to the broker
     * @return a full url like this 'tcp://localhost:1883'
     */
    public String getFullUrl() {
        return String.format("%s://%s:%d", protocol, url, port);
    }
}
