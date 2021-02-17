package at.htl.controller;

import at.htl.entity.Measurement;
import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@ApplicationScoped
public class MeasurementRepository {

    @Inject
    AgroalDataSource dataSource;

    @Transactional
    public Measurement save(Measurement measurement) {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "insert into measurement (timestamp, room, sensor, value) values (?, ?, ?, ?)")
            ) {
                ps.setTimestamp(1, Timestamp.valueOf(measurement.getTimeStamp()));
                ps.setString(2, measurement.getRoom());
                ps.setString(3, measurement.getSensor());
                ps.setInt(4, measurement.getValue());

                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return measurement;
    }
}
