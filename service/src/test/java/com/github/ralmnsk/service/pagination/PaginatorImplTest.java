package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PaginatorImplTest {

    @Mock
    private NewsService newsService;
    @InjectMocks
    private Paginator paginator=new PaginatorImpl();

    private User user;

    private News news;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        news.setUser(user);
        user.getNewsSet().add(news);
    }

    @Test
    void viewNews() {
        Pageable pageable= PageRequest
                .of(0,10, Sort.by("dateNews")
                        .descending());
        paginator.viewNews(0, 10);
        Mockito.verify(newsService, Mockito
                .times(1))
                .findAllNews(pageable);
    }

    @Test
    void getAllNewsCount() {
        paginator.getAllNewsCount();
        Mockito.verify(newsService, Mockito
                .times(1))
                .countAllNews();
    }

    @Test
    void viewNewsOfUser() {
        List<Long> list=new ArrayList<>();
        list.add(1L);
        Pageable pageable= PageRequest.of(0,10);
        when(newsService.findAllNewsByUserId(pageable,user.getId()))
                .thenReturn(list);
        when(newsService.getById(1L)).thenReturn(news);
        paginator.viewNewsOfUser(0, 10,user);
        Mockito.verify(newsService,times(1)).getById(1L);
        Mockito.verify(newsService, Mockito
                .times(1))
                .findAllNewsByUserId(pageable,user.getId());
    }

    @Test
    void pagesCount() {
        int count=paginator.pagesCount(36,10);
        assertTrue(count==4);
        count=paginator.pagesCount(41,10);
        assertTrue(count==5);
    }

//    @Test
//    void viewNews() {
//        PaginatorImpl paginator=mock(PaginatorImpl.class);
//        paginator.viewNews(0,10);
//        verify(paginator,times(1)).viewNews(0,10);
//    }
//
//    @Test
//    void viewNewsOfUser() {
//        PaginatorImpl paginator=mock(PaginatorImpl.class);
//        paginator.viewNewsOfUser(0,10);
//        verify(paginator,times(1)).viewNewsOfUser(0,10);
//    }
//
//    @Test
//    void pagination() {
//        PaginatorImpl paginator=mock(PaginatorImpl.class);
//        paginator.pagination(1);
//        verify(paginator,times(1)).pagination(1);
//    }
//
//    @Test
//    void paginationForUserNews() {
//        PaginatorImpl paginator=mock(PaginatorImpl.class);
//        paginator.paginationForUserNews(1);
//        verify(paginator,times(1)).paginationForUserNews(1);
//    }
}