package dao.user;

import dao.connection.SingletonConnection;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;


public class UserDaoImpl implements UserDao{
    private static Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);
    private SingletonConnection singletonConnection;

    {
        try {
            singletonConnection = SingletonConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
    return singletonConnection.getConnection();
    }

    public void closeConnection(Connection connection) {
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
        }
//        finally {
//            closeConnection(connection);
//        }


    }

    public User readUser(User user) {
        Connection connection = null;
        User newUser=new User();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from USRTAB where name=?",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                newUser.setId(rs.getLong(1));
                newUser.setName(rs.getString(2));
                newUser.setPass(rs.getString(3));
                newUser.setJoinDate(rs.getDate(4));
                newUser.setRole(rs.getString(5));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
//        finally {
//            closeConnection(connection);
//        }
        return newUser;
    }

    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update USRTAB set name=?,pass=?,join_date=?,role=? where name=?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setDate(3, user.getJoinDate());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getName());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
//        finally {
//            closeConnection(connection);
//        }
    }

    public void deleteUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("delete from USRTAB where name=?");
            statement.setString(1, user.getName());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE", ex);
        }
//        finally {
//            closeConnection(connection);
//        }

    }
}
