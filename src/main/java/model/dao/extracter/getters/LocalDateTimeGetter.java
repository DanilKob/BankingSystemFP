package model.dao.extracter.getters;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by Danila on 25.08.2018.
 */
public class LocalDateTimeGetter implements Getter<LocalDateTime>{
    @Override
    public LocalDateTime getValueFrom(ResultSet resultSet, String columnName, Field field) throws SQLException {
        return resultSet.getTimestamp(columnName).toLocalDateTime();
    }
}
