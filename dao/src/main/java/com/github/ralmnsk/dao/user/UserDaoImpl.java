package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.dao.connection.SingletonConnection;
import com.github.ralmnsk.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;


public class UserDaoImpl implements UserDao{
    private static Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);

    private static volatile UserDao instance;

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDaoImpl();
                }
            }
        }
        return localInstance;
    }

    private Connection getConnection() throws SQLException{
    return SingletonConnection.getInstance().getConnection();
    }


    public void createUser(User user) {
        Connection connection= null;

        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection
                            .prepareStatement("insert into usrtab (name, pass, join_date, role) values (?, ?, ?,?)"
                                    , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setTimestamp(3, user.getJoinDate());
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
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in createUser(): connection.close():"+e);
//            }
//        }
    }

    public User readUser(User user) {
        Connection connection= null;

        User newUser=new User();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from usrtab where name=?",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                newUser.setId(rs.getLong(1));
                newUser.setName(rs.getString(2));
                newUser.setPass(rs.getString(3));
                newUser.setJoinDate(rs.getTimestamp(4));
                newUser.setRole(rs.getString(5));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in readUser(): connection.close():"+e);
//            }
//        }
        return newUser;
    }

    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update usrtab set name=?,pass=?,join_date=?,role=? where name=?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setTimestamp(3, user.getJoinDate());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getName());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in updateUser(): connection.close():"+e);
//            }
//        }
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
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in deleteUser(): connection.close():"+e);
//            }
//        }

    }

    @Override
    public User getById(Long id) {
        User user=new User();
        Connection connection= null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from usrtab where id=?",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setPass(rs.getString(3));
                user.setJoinDate(rs.getTimestamp(4));
                user.setRole(rs.getString(5));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UserServiceImpl().getById():", ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in readUser(): connection.close():"+e);
//            }
//        }
        return user;
    }
}
