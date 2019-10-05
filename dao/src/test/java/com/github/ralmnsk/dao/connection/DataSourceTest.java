package com.github.ralmnsk.dao.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceTest {

    @Test
    void getInstance() {
        DataSource dataSource=DataSource.getInstance();
        assertNotNull(dataSource);
    }

    @Test
    void getConnection() {
        DataSource dataSource=DataSource.getInstance();
        Connection connection=dataSource.getConnection();
        assertNotNull(connection);
    }

    @Test
    void metadata() throws SQLException {
        DataSource dataBase = DataSource.getInstance();
        Connection connection = dataBase.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        assertEquals("MySQL Connector/J", metaData.getDriverName());
        assertEquals("mysql-connector-java-8.0.16 (Revision: 34cbc6bc61f72836e26327537a432d6db7c77de6)", metaData.getDriverVersion());
        assertEquals("jdbc:mysql://remotemysql.com:3306/eYcooOkOuh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow",
                metaData.getURL());
        assertEquals("eYcooOkOuh@37.214.44.172", metaData.getUserName());
    }


}