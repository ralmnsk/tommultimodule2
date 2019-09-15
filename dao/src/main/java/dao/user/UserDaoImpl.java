package dao.user;

import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserDaoImpl implements UserDao{

    private static Logger logger= LoggerFactory.getLogger(UserDao.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("Prblem loadng DB dDiver!", ex);
        }
    }

    private Connection getConnection() throws SQLException{
        Properties properties=new Properties();
        try {
            properties
                    .load(getClass()
                            .getClassLoader()
                            .getResourceAsStream("dao.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                properties.getProperty("jdbc"),
                properties.getProperty("user"),
                properties.getProperty("password"));

    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error("Problem closing connection to the database!",ex);
        }
    }


    public void createUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection
                            .prepareStatement("insert into USRTAB (name, pass, join_date, role) values (?, ?, ?,?)"
                                    , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setDate(3, user.getJoinDate());
            statement.setString(4, user.getRole());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Prblem executing INSERT", ex);
        } finally {
            closeConnection(connection);
        }


    }

    public void readUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("delete from usrtab where name=?");
            statement.setString(1, user.getName());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE", ex);
        } finally {
            closeConnection(connection);
        }

    }
}
