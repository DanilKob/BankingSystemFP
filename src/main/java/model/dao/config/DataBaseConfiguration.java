package model.dao.config;

import model.dao.AbstractDaoFactory;
import model.dao.impl.JDBCDaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfiguration {
    // todo singleton???
    private static DataBaseConfiguration instance = new DataBaseConfiguration();

    public static final AbstractDaoFactory factory = new JDBCDaoFactory();

    private Properties properties = loadProperties(ConfigurationKeys.PROPERTIES_PATH);

    private DataBaseConfiguration(){

    }

    public static DataBaseConfiguration getInstance(){
        return instance;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }


    private Properties loadProperties(String path){
        Properties properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)){
            properties.load(inputStream);
        } catch (IOException e) {
            // todo add logger
            e.printStackTrace();
        }
        return properties;
    }

}
