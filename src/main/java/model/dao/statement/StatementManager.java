package model.dao.statement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StatementManager {
    private static StatementManager instance = new StatementManager();
    private StatementManager(){

    }

    public static StatementManager getInstance(){
        return instance;
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
