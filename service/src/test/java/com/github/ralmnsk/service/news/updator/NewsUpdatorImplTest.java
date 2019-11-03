package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsUpdatorImplTest {
    private HttpSession sessionMock=mock(HttpSession.class);

    @Test
    void newsUpdate() {
        News news=new News("test","test",new Date());
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        newsDao.createNews(news);
        News readNews=newsDao.readNews(news);
        readNews.setNameNews("test2");
        NewsUpdatorImpl newsUpdator=new NewsUpdatorImpl(sessionMock,readNews);
        newsUpdator.newsUpdate();
        News testNews=newsDao.getById(readNews.getIdNews());
        newsUpdator.setNews(readNews);
        assertTrue(newsUpdator.getNews().getNameNews().equals("test2"));
        assertTrue(newsUpdator.getNews().getNameNews().equals(testNews.getNameNews()));
        assertTrue(testNews.getNameNews().equals(readNews.getNameNews()));
        newsDao.deleteNews(testNews);
    }
}