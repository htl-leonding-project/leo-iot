# Backend

## Local Testing

**First of all, we need to start the postgres database:**
```shell script
sudo docker-compose -f docker-compose.db.yml up
```
 
**Setup the connection:**

Host: localhost 

Port: 5432

User: app

Password: app

**Get Quarkus running:**
```shell script
./mvnw clean compile quarkus:dev
```

**Open the Mqtt-Explorer and setup a connection:** 

_Host:_ vm90.htl-leonding.ac.at

_Port:_ 8443

Encryption should be checked on and Validate certificate off

**Publishing demo values:**

For testing purposes you use this path/topic for example:
```shell script
og/107/noise/test
```

It's important to note that it doesn't matter which room and subtopic you use, but it's necessary to add the test topic at the end.

The published values look like this:
```json
{"timestamp":1613767649,"value":1.00}
```

Those added values should be now displayed in the measurement table.