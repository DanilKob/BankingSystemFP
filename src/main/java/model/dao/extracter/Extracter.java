package model.dao.extracter;




import model.dao.extracter.getters.*;
import model.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Extracter<T extends Entity> {

    private Map<String,Field> fieldMap = new HashMap<>();

    private List<String> columnNames = new LinkedList<>();

    private static Map<Class,Getter> setterMap = new HashMap<>();

    static {
        setterMap.put(int.class, new IntGetter());
        setterMap.put(String.class, new StringGetter());
        setterMap.put(Enum.class, new EnumGetter());
        setterMap.put(LocalDateTime.class, new LocalDateTimeGetter());
    }

    public T extractFromResultSet(ResultSet rs, T entity) throws IllegalAccessException, SQLException {

        setColumnNames(rs);
        setAnnotatedFields(entity);
        Field field;
        for (String columnName : columnNames) {
            if(fieldMap.containsKey(columnName)){
                field = fieldMap.get(columnName);
                field.set(entity,setterMap.get(getType(field)).getValueFrom(rs,columnName, field));
            }
        }
        return entity;
    }


    private void setColumnNames(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for(int index=1;index<=columnCount;++index){
            String columnName = resultSetMetaData.getColumnName(index);
            columnNames.add(columnName);
        }
    }

    private void setAnnotatedFields(T entity){

        Class<?> clazz = entity.getClass();

        List<Field> fieldList = getSuperClassFieldsRecursively(clazz);

        for (Field field : fieldList) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(ExtractParam.class)){
                fieldMap.put(field.getAnnotation(ExtractParam.class).columnName(),field);
                //System.out.println("Equal field = " + field.getName());
            }
        }
    }

    private Type getType(Field field){
        if(field.getType().isEnum()){
            return Enum.class;
        }
        return field.getType();
    }

    private List<Field> getSuperClassFieldsRecursively(Class clazz){
        //System.out.println("Func called for class = " + clazz.getName());
        List<Field> fieldList = new LinkedList<>();
        fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if(clazz.getSuperclass().equals(Object.class) || Arrays.stream(clazz.getInterfaces()).anyMatch(Entity.class::equals)){
            return fieldList;
        }else{
            fieldList.addAll(getSuperClassFieldsRecursively(clazz.getSuperclass()));
            return fieldList;
        }
    }
}
