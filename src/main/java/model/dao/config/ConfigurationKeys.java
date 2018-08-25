package model.dao.config;

public interface ConfigurationKeys {
    String PROPERTIES_PATH = "database.properties";
    String DATA_BASE_URL = "url";
    String LOGIN = "login";
    String PASSWORD = "password";

    String CONNECTION_MAX = "connections.max";
    String CONNECTION_MIN = "connections.min";
    String STATEMENT_MAX = "prepared.statements";

}
