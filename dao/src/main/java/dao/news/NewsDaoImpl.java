package dao.news;

import dao.connection.SingletonConnection;
import dao.user.UserDaoImpl;
import model.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class NewsDaoImpl implements NewsDao {
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

    public void createNews(News news) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection
                            .prepareStatement("insert into newstab (namenews, datanews, date) values (?, ?, ?)"
                                    , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, news.getNameNews());
            statement.setString(2, news.getDataNews());
            statement.setDate(3, news.getDateNews());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                news.setIdNews(generatedKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Prblem executing INSERT", ex);
        }
//        finally {
//            closeConnection(connection);
//        }
    }

    public News readNews(News news) {
        return null;
    }

    public void updateNews(News news) {

    }

    public void deleteNews(News news) {

    }
}
