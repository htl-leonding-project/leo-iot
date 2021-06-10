package at.htl.repository;

import at.htl.entity.Measurement;
import at.htl.entity.Sensor;
import at.htl.util.mqtt.MqttParseCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.sql.Timestamp;
import java.util.List;

@ApplicationScoped
public class MeasurementRepository
        extends
            Repository<Measurement, Measurement.MeasurementKey>
        implements
            MqttParseCallback<Measurement>
{

    public List<Measurement> get(Timestamp from, Timestamp to, Sensor sensor) throws IllegalArgumentException {
        if (from.after(to)) throw new IllegalArgumentException();
        var query = getEntityManager().createQuery(
                "select m " +
                        "from Measurement m " +
                        "where m.measurementKey.timestamp >= :fromTimestamp " +
                        "and m.measurementKey.timestamp <= :toTimestamp " +
                        "and m.measurementKey.sensor = :sensor",
                Measurement.class
        );

        query.setParameter("fromTimestamp", from);
        query.setParameter("toTimestamp", to);
        query.setParameter("sensor", sensor);

        return query.getResultList();
    }

    public List<Measurement> get(Timestamp from, Timestamp to) throws IllegalArgumentException {
        if (from.after(to)) throw new IllegalArgumentException();
        var query = getEntityManager().createQuery(
                "select m " +
                        "from Measurement m " +
                        "where m.measurementKey.timestamp >= :fromTimestamp " +
                        "and m.measurementKey.timestamp <= :toTimestamp",
                Measurement.class
        );

        query.setParameter("fromTimestamp", from);
        query.setParameter("toTimestamp", to);

        return query.getResultList();
    }

    @Override
    public Measurement parseFromMqtt(String topic, MqttMessage message) {
        Measurement measurement = new Measurement();
        JsonObject object = JsonbBuilder
                .create()
                .fromJson(new String(message.getPayload()), JsonObject.class);

        measurement.setMeasurementKey(new Measurement.MeasurementKey(
                // * 1000 for converting seconds to milliseconds
                new Timestamp(object.getJsonNumber("timestamp").longValue() * 1000),
                null
        ));

        measurement.setValue(object.getJsonNumber("value").doubleValue());

        return measurement;
    }
}
