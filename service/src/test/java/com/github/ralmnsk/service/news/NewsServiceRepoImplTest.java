package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceRepoImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

//    @Test
//    void findAllNews() {
//        News news=new News("testNewsName","testNewsData",new Date());
//        List<News> list=new ArrayList<News>();
//        list.add(news);
//        when(repo.findAll(PageRequest.of(0,10)).getContent()).thenReturn(list);
//        List<News> testList=service.findAllNews(0,10);
//        assertTrue(testList.get(0).getNameNews().equals("testNewsName"));
//    }

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

//    @Test
//    void getById() {
//        service.getById(1L);
//        Mockito.verify(repo,times(1)).findById(1L);
//    }
}