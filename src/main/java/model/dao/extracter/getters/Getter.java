package model.dao.extracter.getters;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Danila on 24.08.2018.
 */
public interface Getter<V> {
    public V getValueFrom(ResultSet resultSet, String columnName, Field field) throws SQLException;
}
