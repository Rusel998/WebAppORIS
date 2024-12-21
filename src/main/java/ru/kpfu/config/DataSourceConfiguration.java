package ru.kpfu.config;


import lombok.AllArgsConstructor;
import ru.kpfu.util.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@AllArgsConstructor
public class DataSourceConfiguration {
    private Properties properties;
    public DataSource customDatasource(){
        try {
            Class.forName(properties.getProperty("database.driver-name"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found: " + properties.getProperty("database.driver-name"), e);
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.username"));
        dataSource.setPassword(properties.getProperty("database.password"));

        return dataSource;
    }

}
