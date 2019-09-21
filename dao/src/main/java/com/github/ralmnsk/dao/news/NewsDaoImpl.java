package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.SingletonConnection;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private static Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);

    private Connection getConnection() throws SQLException{
        return SingletonConnection.getInstance().getConnection();
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
        Connection connection= null;

        News newNews=new News();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from newstab where namenews=?",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, news.getNameNews());
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();

            while(rs.next()){
                newNews.setIdNews(rs.getLong(1));
                newNews.setNameNews(rs.getString(2));
                newNews.setDataNews(rs.getString(3));
                newNews.setDateNews(rs.getDate(4));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing readNews", ex);
        }
        return newNews;
    }

    public void updateNews(News news) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update newstab set datanews=?,date=? where namenews=?");
            statement.setString(1, news.getDataNews());
            statement.setDate(2, news.getDateNews());
            statement.setString(3, news.getNameNews());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
    }

    public void deleteNews(News news) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("delete from newstab where namenews=?");
            statement.setString(1, news.getNameNews());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE", ex);
        }
    }

    public List<News> findAllNews() {
        Connection connection= null;

        List<News> newsList=new ArrayList<News>();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from newstab ",Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();

            while(rs.next()){
                News news=new News();
                news.setIdNews(rs.getLong(1));
                news.setNameNews(rs.getString(2));
                news.setDataNews(rs.getString(3));
                news.setDateNews(rs.getDate(4));
                newsList.add(news);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing readNews", ex);
        }

        return newsList;
    }
}
