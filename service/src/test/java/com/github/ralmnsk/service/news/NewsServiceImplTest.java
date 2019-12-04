package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class NewsServiceImplTest {
//    @Mock
//    public NewsRepository newsRepository; //=NewsDaoImpl.getInstance();
//
//    @InjectMocks
//    public NewsService newsService; //=NewsServiceImpl.getInstance();
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    public Date time=new Date();
//
//    public News getMeTestNews(){
//        News news=new News("nameNews","dataNews",time);
//        return news;
//    }
//
//    @Test
//    public void createNews() {
//        News news=getMeTestNews();
//        newsService.createNews(news);
//        Mockito.verify(newsDao,times(1)).createNews(news);
//    }
//
//    @Test
//    public void readNews() {
//        News news=getMeTestNews();
//        newsService.readNews(news);
//        Mockito.verify(newsDao,times(1)).readNews(news);
//    }
//
//    @Test
//    public void getById() {
//        News news=getMeTestNews();
//        when(newsDao.getById(1000L)).thenReturn(news);
//        News testNews=newsService.getById(1000L);
//        assertEquals(testNews,news);
//        Mockito.verify(newsDao,times(1)).getById(1000L);
//    }

//    @Test
//    public void updateNews() {
//        News news=getMeTestNews();
//        NewsDaoHiberImpl newsDao=mock(NewsDaoHiberImpl.class);
//        NewsService newsService=NewsServiceImpl.getInstance();
//        //newsService.setNewsDao(newsDao);
//        news.setDataNews("dataNews222");
//        newsService.updateNews(news);
//        verify(newsDao,times(1)).updateNews(news);
//    }


//
//    @Test
//    public void deleteNews() {
//        News newsOne=new News("nameNews","dataNews",time);
//        newsService.deleteNews(newsOne);
//        verify(newsDao,times(1)).deleteNews(newsOne);
//    }
//
//    @Test
//    public void findAllNews() {
//        List<News> list=new ArrayList<News>();
//        News newsOne=new News("nameNews","dataNews",time);
//        News newsTwo=new News("nameNews2","dataNews2",time);
//        list.add(newsOne);
//        list.add(newsTwo);
//        when(newsDao.findAllNews(0,10)).thenReturn(list);
//        List<News> newsList=newsService.findAllNews(PageRequest.of(0,10));
//        assertEquals(2,newsList.size());
//        assertEquals(newsOne,newsList.get(0));
//        verify(newsDao,times(1)).findAllNews(0,10);
//    }
}