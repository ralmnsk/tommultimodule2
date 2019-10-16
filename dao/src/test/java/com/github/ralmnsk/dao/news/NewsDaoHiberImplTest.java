package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class NewsDaoHiberImplTest {
    private NewsDao newsDao=NewsDaoHiberImpl.getInstance();


    @Test
    void getInstance() {
        assertNotNull(newsDao);
    }


    @Test
    void createNews() {
        News news=new News(1001L,"nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void readNews() {
        News news=new News(1001L,"nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void updateNews() {
        News news=new News(1001L,"nameNews","dataNews",new Date());
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
        News news=new News(1001L,"nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
        News deletedNews=newsDao.readNews(foundNews);
        assertNull(deletedNews);
    }

    @Test
    void findAllNews() {
        for (int i=0; i<20;i++){
            News news=new News(1000L+i,1001L,"nameNews"+i,"dataNews"+i,new Date());
            newsDao.createNews(news);
        }
        List<News> list=newsDao.findAllNews(0,10);
        assertTrue(list.size()>20);
        list.stream().forEach(System.out::println);
        //list.stream().forEach(news->newsDao.deleteNews(news));
    }

    @Test
    void getUserId() {
        News news=new News(1001L,"nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        Long userId=newsDao.getUserId(testNews.getIdNews());
        assertEquals(1001L,userId);
        newsDao.deleteNews(testNews);
    }

    @Test
    void getById() {
        News news=new News(1000L,"nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        Long newsId=testNews.getIdNews();
        News newsGetById=newsDao.getById(newsId);
        assertEquals(testNews.getNameNews(),newsGetById.getNameNews());
        assertEquals(testNews.getDataNews(),newsGetById.getDataNews());
        newsDao.deleteNews(testNews);
    }
}