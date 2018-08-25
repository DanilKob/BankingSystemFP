package model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericMapper<K,V>{
    private Map<K,V> cache = new HashMap<>();

    public abstract V extractFromResultSet(ResultSet rs) throws SQLException;

    public abstract K getKey(V entity);

    public V extractUniqueFromResultSet(ResultSet rs) throws SQLException{
        V entity = extractFromResultSet(rs);
        cache.putIfAbsent(getKey(entity),entity);
        return entity;
    }
}
