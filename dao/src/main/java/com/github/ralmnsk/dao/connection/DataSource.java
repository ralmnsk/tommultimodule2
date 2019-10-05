package com.github.ralmnsk.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static DataSource dataSource;
    private ComboPooledDataSource cpds;
    private static Logger logger= LoggerFactory.getLogger(DataSource.class);

    private DataSource() {
        Properties properties=new Properties();
        cpds=new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            properties.load(getClass().getClassLoader()
                                      .getResourceAsStream("dao.properties"));
            cpds.setJdbcUrl(properties.getProperty("jdbc"));
            cpds.setUser(properties.getProperty("user"));
            cpds.setPassword(properties.getProperty("password"));
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            logger.error("DataSource problem:",e);
        } catch (IOException e) {
            logger.error("DataSource problem:",e);
        }
    }

    public static DataSource getInstance(){
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        }
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return this.cpds.getConnection();
        } catch (SQLException e) {
            logger.error("Exception in DataSource.getConnection",e);
            return null;
        }
    }

}
