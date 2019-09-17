package dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnection {
    private static SingletonConnection INSTANCE=null;
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
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.connection=DriverManager.getConnection(
                    properties.getProperty("jdbc"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (ClassNotFoundException ex) {
            logger.error("Prblem loadng DB dDiver!", ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static SingletonConnection getInstance() throws SQLException {
        if (INSTANCE == null) {
            INSTANCE = new SingletonConnection();
            INSTANCE.getConnection();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

}
