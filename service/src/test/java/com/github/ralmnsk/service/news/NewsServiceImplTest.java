package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class NewsServiceImplTest {
    @Mock
    public NewsDaoImpl newsDao; //=NewsDaoImpl.getInstance();

    @InjectMocks
    public NewsServiceImpl newsService; //=NewsServiceImpl.getInstance();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public Timestamp time=new Timestamp(new java.util.Date().getTime());

    public News getMeTestNews(){
        News news=new News(1000L,1001L,"nameNews","dataNews",time);
        return news;
    }

    @Test
    public void createNews() {
        newsService.createNews(getMeTestNews());
        Mockito.verify(newsDao);
    }

    @Test
    public void readNews() {
        newsService.readNews(getMeTestNews());
        Mockito.verify(newsDao);
    }

    @Test
    public void getById() {
        newsService.getById(1000L);
        when(newsService.getById(1000L)).thenReturn(getMeTestNews());
        assertSame("nameNews",newsService.getById(1000L).getNameNews());
    }

    @Test
    public void updateNews() {
        News news=getMeTestNews();
        news.setDataNews("dataNews222");
        newsService.updateNews(news);
        when(newsService.readNews(news)).thenReturn(news);
        assertSame("dataNews222", newsService.readNews(news).getDataNews());
    }

    @Test
    public void deleteNews() {
        News news=getMeTestNews();
        newsService.deleteNews(news);
        when(newsService.readNews(news)).thenReturn(null);
        assertNull(newsService.readNews(news));
    }

    @Test
    public void findAllNews() {
        newsService.findAllNews();
        verify(newsDao);
    }
}