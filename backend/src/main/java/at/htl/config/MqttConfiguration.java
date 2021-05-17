package at.htl.config;

import io.quarkus.arc.config.ConfigProperties;

import java.util.Optional;

@ConfigProperties
public class MqttConfiguration {

    public String url = "localhost";
    public String protocol = "tcp";
    public Optional<String> password;
    public Optional<String> username;

    public String getFullUrl() {
        return String.format("%s://%s", protocol, url);
    }
}
