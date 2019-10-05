package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.DataSource;
import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private static Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);
    private static volatile NewsDao instance;


    public static NewsDao getInstance() {
        NewsDao localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NewsDaoImpl();
                }
            }
        }
        return localInstance;
    }


    private Connection getConnection(){
        return DataSource.getInstance().getConnection();
    }


    public void createNews(News news) {
        ResultSet generatedKeys=null;
        try
            (Connection connection = getConnection();
            PreparedStatement statement =
               connection.prepareStatement
                  ("insert into newstab (namenews, datanews, date) values (?, ?, ?)"
                                    , Statement.RETURN_GENERATED_KEYS);

            )
        {
            statement.setString(1, news.getNameNews());
            statement.setString(2, news.getDataNews());
            statement.setTimestamp(3, news.getDateNews());
            statement.execute();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                news.setIdNews(generatedKeys.getLong(1));
            }
            generatedKeys.close();
            logger.info(this.getClass()+" createNews");
        } catch (SQLException ex) {
            logger.error("Problem executing createNews", ex);
        }finally {
            try {
                generatedKeys.close();
            } catch (SQLException e) {
                logger.error("Problem executing createNews. generatedKeys close", e);
            }
        }
    }

    public News readNews(News news) {
        News newNews=new News();
        ResultSet rs=null;
        try
            (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                    ("select * from newstab where namenews=?",Statement.RETURN_GENERATED_KEYS);
            )
        {
            statement.setString(1, news.getNameNews());
            rs = statement.executeQuery();     //   getGeneratedKeys();
            //statement.execute();
            while(rs.next()){
                newNews.setIdNews(rs.getLong(1));
                newNews.setNameNews(rs.getString(2));
                newNews.setDataNews(rs.getString(3));
                newNews.setDateNews(rs.getTimestamp(4));
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing readNews", ex);
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing readNews, rs close", e);
                }
            }
        }
        return newNews;
    }

    public void updateNews(News news) {
        try
            (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update newstab set namenews=?,datanews=?,date=? where idnews=?");
            )

        {
            statement.setString(1, news.getNameNews());
            statement.setString(2, news.getDataNews());
            statement.setTimestamp(3, news.getDateNews());
            statement.setLong(4, news.getIdNews());
            statement.execute();
//            statement.close();
            logger.info(this.getClass()+" udateNews");
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
    }

    public void deleteNews(News news) {
        try (
             Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                            ("delete from newstab where namenews=?");
                )

        {
            statement.setString(1, news.getNameNews());
            statement.execute();
            //statement.close();
            logger.info(this.getClass()+" deleteNews");
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE", ex);
        }
    }

    public List<News> findAllNews() {
        List<News> newsList=new ArrayList();
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                    ("select * from newstab ",Statement.RETURN_GENERATED_KEYS);
                )
        {
                rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                News news=new News();
                Long id=rs.getLong(1);
                news.setIdNews(id);
                news.setNameNews(rs.getString(2));
                news.setDataNews(rs.getString(3));
                news.setDateNews(rs.getTimestamp(4));
                news.setIdUser(getUserId(id));
                newsList.add(news);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing findAllNews", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing findAllNews, rs close", e);
                }
            }
        }

        return newsList;
    }

    public Long getUserId(Long newsId){
        StorageDao storageDao=new StorageDaoImpl();
        Long userId=storageDao.getUserIdByNewsId(newsId);
        return userId;
    }

    @Override
    public News getById(Long id) {
        News news=new News();
        ResultSet rs=null;
        try (
             Connection connection=getConnection();
             PreparedStatement statement = connection.prepareStatement
                    ("select * from newstab where idnews=?",Statement.RETURN_GENERATED_KEYS);
             )
        {
            statement.setLong(1,id);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
            while(rs.next()){
                news.setIdNews(rs.getLong(1));
                news.setNameNews(rs.getString(2));
                news.setDataNews(rs.getString(3));
                news.setDateNews(rs.getTimestamp(4));
            }
            rs.close();
            news.setIdUser(getUserId(id));
            logger.info(this.getClass()+"News getById");
        } catch (SQLException ex) {
            logger.error("Problem executing News getById", ex);
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing News getById, rs close", e);
                }
            }
        }
        return news;
    }
}
