package model.dao.mapper;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper<V> {
    private Map<Object,V> cache = new HashMap<>();

    private Field fieldId = null;

    public Object getKey(V entity){
        try{
            return getFieldId(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("catch Illegal exception");
        }
        return null;
    }

    public V makeUnique(V entity){
        cache.putIfAbsent(getKey(entity),entity);
        return cache.get(getKey(entity));
    }

    public void addInCashe(V entity){
        cache.putIfAbsent(getKey(entity),entity);
    }

    public Collection<V> getUnigueEntities(){
        return cache.values();
    }

    public Collection<V> getUnigueEntities(List<V> entities){
        for (V entity : entities) {
            addInCashe(entity);
        }

        return cache.values();
    }

    private Object getFieldId(V entity) throws IllegalAccessException{
        if(fieldId == null){
            Class<?> userClass =  entity.getClass();

            Field[] fields = userClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.isAnnotationPresent(MappingKey.class)){
                    fieldId = field;
                }
            }
        }
        return fieldId.get(entity);
    }
}
