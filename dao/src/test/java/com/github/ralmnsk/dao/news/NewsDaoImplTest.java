package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.model.news.News;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;

public class NewsDaoImplTest {
    private News news;

    @Before
    public void setUp(){
        Properties properties=new Properties();
        try {
            properties
                    .load(getClass()
                            .getClassLoader()
                            .getResourceAsStream("daotest.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nameNews=properties.getProperty("namenews");
        String dataNews=properties.getProperty("datanews");
        news=new News(1l,nameNews,dataNews,new Timestamp(new java.util.Date().getTime()));
    }



    public void createNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        newsDao.createNews(news);
    }


    public void readNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        News readNews = newsDao.readNews(this.news);
        System.out.println(readNews.toString());
        assertNotNull(readNews);
        assertEquals(readNews.getNameNews(),news.getNameNews());
        assertEquals(readNews.getDataNews(),news.getDataNews());
    }


    public void updateNews() {
        News newsChanging=new News(1000L,news.getNameNews(),"new news data 1234567", new Timestamp(new java.util.Date().getTime()));
        System.out.println(newsChanging);
        NewsDaoImpl newsDao=new NewsDaoImpl();

        System.out.println(newsChanging);
        newsDao.updateNews(newsChanging);

        assertNotNull(newsChanging);
        assertEquals(newsChanging.getNameNews(),news.getNameNews());
        assertNotEquals(newsChanging.getDataNews(),news.getDataNews());
    }


    public void deleteNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        newsDao.deleteNews(news);
    }

    @Test
    public void testing(){
       createNews();
       readNews();
       updateNews();
       deleteNews();
    }

    @Test
    public void findAllNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        List<News> newsList=newsDao.findAllNews();
        for (News n:newsList) {
            System.out.println(n.toString());
        }
    }
}