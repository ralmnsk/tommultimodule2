package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class NewsDaoImplTest {
    private NewsDao newsDao=NewsDaoImpl.getInstance();


    @Test
    void getInstance() {
        assertNotNull(newsDao);
    }


    @Test
    void createNews() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void readNews() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void updateNews() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        foundNews.setDataNews("changedTestData");
        newsDao.updateNews(foundNews);
        News updagtedNews=newsDao.readNews(foundNews);
        assertEquals("changedTestData",updagtedNews.getDataNews());
        newsDao.deleteNews(updagtedNews);
    }

    @Test
    void deleteNews() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
        News deletedNews=newsDao.readNews(foundNews);
        assertNull(deletedNews.getIdNews());
        assertNull(deletedNews.getNameNews());
        assertNull(deletedNews.getDataNews());

    }

    @Test
    void findAllNews() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        List<News> list=newsDao.findAllNews();
        assertTrue(list.size()>0);
        newsDao.deleteNews(news);
    }

    @Test
    void getUserId() {
        News news=new News(1000L,1001L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        StorageDao storageDao= StorageDaoImpl.getInstance();
        storageDao.createStorage(333L,testNews.getIdNews());
        Long userId=newsDao.getUserId(testNews.getIdNews());
        assertEquals(333L,userId);
        storageDao.deleteStorage(333L,testNews.getIdNews());
        newsDao.deleteNews(testNews);
    }

    @Test
    void getById() {
        News news=new News(1000L,"nameNews","dataNews",new Timestamp(new java.util.Date().getTime()));
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        Long newsId=testNews.getIdNews();
        News newsGetById=newsDao.getById(newsId);
        assertEquals(testNews.getNameNews(),newsGetById.getNameNews());
        assertEquals(testNews.getDataNews(),newsGetById.getDataNews());
        newsDao.deleteNews(testNews);
    }
}