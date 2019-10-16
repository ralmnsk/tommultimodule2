package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.HibernateUtil;
import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NewsDaoHiberImpl implements NewsDao {
    private static Logger logger= LoggerFactory.getLogger(NewsDaoHiberImpl.class);
    private static volatile NewsDao instance;


    public static NewsDao getInstance() {
        NewsDao localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NewsDaoHiberImpl();
                }
            }
        }
        return localInstance;
    }

    public void createNews(News news) {    //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(news);
        session.getTransaction().commit();
        session.close();
    }

    public News readNews(News news) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        News readNews=null;
           readNews=session.get(News.class,news.getIdNews());
        session.getTransaction().commit();
        session.close();
        return readNews;
    }

    public void updateNews(News news) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        if(readNews(news)!=null){
            session.update(news);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteNews(News news) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        News readNews=session.get(News.class,news.getIdNews());
        session.delete(readNews);
        session.getTransaction().commit();
        session.close();
    }

    public List<News> findAllNews(Long start, Long end) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<News> query=session.createQuery("from News "); //User is @Entity where @Column (name="usr_name")
        List<News> newsList=query.list();

        session.getTransaction().commit();
        session.close();
        return newsList;
    }

    public Long getUserId(Long newsId){
        StorageDao storageDao=new StorageDaoImpl();
        Long userId=storageDao.getUserIdByNewsId(newsId);
        return userId;
    }

    @Override
    public News getById(Long id) {
        News news=null;

        return news;
    }
}
