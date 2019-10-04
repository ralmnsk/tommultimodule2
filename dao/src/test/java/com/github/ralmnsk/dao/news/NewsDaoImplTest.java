package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class NewsDaoImplTest {
    private News news;


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
        NewsDao newsDao=NewsDaoImpl.getInstance();
        newsDao.createNews(news);
    }


    public void readNews() {
        NewsDao newsDao=NewsDaoImpl.getInstance();
        News readNews = newsDao.readNews(this.news);
        assertNotNull(readNews);
        assertEquals(readNews.getNameNews(),news.getNameNews());
        assertEquals(readNews.getDataNews(),news.getDataNews());
    }



    @Mock
    private NewsDao newsDao;

    @Test
    public void updateNews() {
        initMocks(this);
        NewsDao mockNewsDao=Mockito.mock(NewsDaoImpl.class);
        mockNewsDao.updateNews(news);
        Mockito.verify(mockNewsDao).updateNews(news);
    }


    public void deleteNews() {
        NewsDao newsDao=NewsDaoImpl.getInstance();
        newsDao.deleteNews(news);
    }

    @Test
    public void testing(){
        setUp();
       createNews();
       readNews();
       updateNews();
       deleteNews();
    }

    @Test
    public void findAllNews() {
        NewsDao newsDao=NewsDaoImpl.getInstance();
        List<News> newsList=newsDao.findAllNews();
        Assertions.assertTrue(newsList.size()>0);
    }

    @Test
    public void getById() {
        setUp();
        NewsDaoImpl newsDao= Mockito.mock(NewsDaoImpl.class);
        Mockito.when(newsDao.getById(1L)).thenReturn(news);
        assertEquals("News text 333.News text 333.",newsDao.getById(1L).getDataNews());
    }
}