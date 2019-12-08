package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.model.news.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
class NewsServiceRepoImplTest {
    @Mock
    private NewsRepository repo;

    @InjectMocks
    private NewsService service=new NewsServiceRepoImpl(repo);

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void createNews() {
        News news=new News("testNewsName","testNewsData",new Date());
        service.createNews(news);
        Mockito.verify(repo,times(1)).save(news);
    }

    @Test
    void readNewsByName() {
        News news=new News("testNewsName","testNewsData",new Date());
        service.readNews(news);
        Mockito.verify(repo,times(1)).findByName(news.getNameNews());
    }

    @Test
    void updateNews() {
        News news=new News("testNewsName","testNewsData",new Date());
        service.updateNews(news);
        Mockito.verify(repo,times(1)).save(news);
    }

    @Test
    void deleteNews() {
        News news=new News("testNewsName","testNewsData",new Date());
        service.deleteNews(news);
        Mockito.verify(repo,times(1)).delete(news);
    }

    @Test
    void findAllNewsByUserId() {
        News news=new News(1L,"testNewsName","testNewsData",new Date());
        List<News> list=new ArrayList<>();
        list.add(news);
        Pageable pageable= PageRequest.of(0,10);
        Page<News> page=new PageImpl<News>(list);
        when(repo.findAll(pageable)).thenReturn(page);
        service.findAllNewsByUserId(pageable, 1L);
        Mockito.verify(repo,times(1)).findAllNewsByUserId(1L);
    }

    @Test
    void findAllNews() {
        News news=new News(1L,"testNewsName","testNewsData",new Date());
        List<News> list=new ArrayList<>();
        list.add(news);
        Pageable pageable= PageRequest.of(0,10);
        Page<News> page=new PageImpl<News>(list);
        when(repo.findAll(pageable)).thenReturn(page);
        service.findAllNews(pageable);
        Mockito.verify(repo,times(1)).findAll(pageable);
    }

    @Test
    void findByName() {
        News news=new News("testNewsName","testNewsData",new Date());
        service.findByName("testNewsName");
        Mockito.verify(repo,times(1)).findByName("testNewsName");
    }

    @Test
    void countAllNews() {
        service.countAllNews();
        Mockito.verify(repo,times(1)).countAllNews();
    }

    @Test
    void getById() {
        News news=new News(1L,"name","text",new Date());
        Optional<News> optional= Optional.of(news);
        when( repo.findById(1L)).thenReturn(optional);

        service.getById(1L);
        Mockito.verify(repo,times(1)).findById(1L);
    }
}