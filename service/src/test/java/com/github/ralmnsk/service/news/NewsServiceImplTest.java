package com.github.ralmnsk.service.news;

import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class NewsServiceImplTest {
    @Mock
    public NewsDaoImpl newsDao; //=NewsDaoImpl.getInstance();

    @InjectMocks
    public NewsServiceImpl newsService; //=NewsServiceImpl.getInstance();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public Timestamp time=new Timestamp(0);

    public News getMeTestNews(){
        News news=new News(1000L,1001L,"nameNews","dataNews",time);
        return news;
    }

    @Test
    public void createNews() {
        News news=getMeTestNews();
        newsService.createNews(news);
        Mockito.verify(newsDao,times(1)).createNews(news);
    }

    @Test
    public void readNews() {
        News news=getMeTestNews();
        newsService.readNews(news);
        Mockito.verify(newsDao,times(1)).readNews(news);
    }

    @Test
    public void getById() {
        News news=getMeTestNews();
        when(newsService.getById(1000L)).thenReturn(news);
        News testNews=newsService.getById(1000L);
        assertEquals(testNews,news);
    }

    @Test
    public void updateNews() {
        News news=getMeTestNews();
        NewsDaoImpl newsDao=mock(NewsDaoImpl.class);
        NewsService newsService=NewsServiceImpl.getInstance();
        newsService.setNewsDao(newsDao);
        news.setDataNews("dataNews222");
        newsService.updateNews(news);
        verify(newsDao,times(1)).updateNews(news);
    }



    @Test
    public void deleteNews() {
        News newsOne=new News(1000L,1001L,"nameNews","dataNews",time);
//        News newsTwo=new News(1000L,1001L,"nameNews","dataNews",time);
//        when(newsDao.readNews(newsOne)).thenReturn(newsTwo);
        newsService.deleteNews(newsOne);
        verify(newsDao,times(1)).deleteNews(newsOne);
//        newsService.deleteNews(news);
//        when(newsService.readNews(news)).thenReturn(null);
//        assertNull(newsService.readNews(news));
    }

    @Test
    public void findAllNews() {
        List<News> list=new ArrayList<News>();
        News newsOne=new News(1000L,1001L,"nameNews","dataNews",time);
        News newsTwo=new News(1001L,1001L,"nameNews2","dataNews2",time);
        list.add(newsOne);
        list.add(newsTwo);
        when(newsService.findAllNews()).thenReturn(list);
        List<News> newsList=newsService.findAllNews();
        assertEquals(2,newsList.size());
        assertEquals(newsOne,newsList.get(0));
        verify(newsDao,times(1)).findAllNews();
    }
}