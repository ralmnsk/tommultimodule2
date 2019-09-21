package com.github.ralmnsk.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnection {
    private static SingletonConnection INSTANCE;
    private Connection connection;

    private static Logger logger= LoggerFactory.getLogger(SingletonConnection.class);

    private  SingletonConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties=new Properties();
            try {
                properties
                        .load(getClass()
                                .getClassLoader()
                                .getResourceAsStream("dao.properties"));
                logger.info("properties in "+this.getClass()+": "+properties.get("jdbc")+" "+properties.get("user")+" "+properties.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.connection=DriverManager.getConnection(
                    properties.getProperty("jdbc"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
            logger.info("connection:"+this.connection);
        } catch (ClassNotFoundException ex) {
            logger.error("Prblem loadng DB dDiver!", ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static SingletonConnection getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new SingletonConnection();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

}
