package model.dao.extracter.getters;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Danila on 24.08.2018.
 */
public class EnumGetter implements Getter<Enum<?>>{
    @Override
    public Enum getValueFrom(ResultSet resultSet, String columnName, Field field) throws SQLException {
        return getEnumByString(resultSet,columnName,field);
    }

    private Enum getEnumByString(ResultSet resultSet, String columnName, Field field) throws SQLException{
        Enum[] enums = (Enum[]) field.getType().getEnumConstants();
        for (Enum anEnum : enums) {
            System.out.println(anEnum.name());
            if(resultSet.getString(columnName).toUpperCase().equals(anEnum.name())){
                return anEnum;
            }
        }
        return null;
    }
}
