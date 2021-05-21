package at.htl.util;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;

@ApplicationScoped
public class TimeStampUtil {

    public Timestamp createTimeStamp(long seconds) {
        return new Timestamp(seconds * 1000);
    }

}
