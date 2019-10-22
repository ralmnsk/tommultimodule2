package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.HibernateUtil;
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

    @Override
    public void createNews(News news) {    //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(news);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public News readNews(News news) {   //retrofitted  it finds only first news with name=newsName
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        News readNews=null;
//           readNews=session.get(News.class,news.getIdNews());
        Query query=session.createQuery("from News where nws_name = :newsName",News.class);
        query.setParameter("newsName",news.getNameNews());
        List<News> newsList=query.getResultList();
        if ((newsList!=null)&&(newsList.size())>0){
            readNews=newsList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return readNews;
    }

    @Override
    public void updateNews(News news) {   //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        if(readNews(news)!=null){
            session.update(news);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteNews(News news) {     //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        News readNews=session.get(News.class,news.getIdNews());
        session.delete(readNews);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<News> findAllNews(int firstResult, int maxResults) {//retrofitted , the first result index equals zero
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<News> query=session.createQuery("from News ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<News> newsList=query.list();

        session.getTransaction().commit();
        session.close();
        return newsList;
    }

    @Override
    public News getById(Long id) {  //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        News news=null;
        news=session.get(News.class,id);
        news.getMsgSet().size();
        session.getTransaction().commit();
        session.close();

        return news;
    }
}
