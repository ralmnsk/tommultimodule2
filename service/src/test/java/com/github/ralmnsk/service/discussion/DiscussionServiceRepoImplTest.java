package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dao.discussion.DiscussionRepository;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DiscussionServiceRepoImplTest {
    @Mock
    private DiscussionRepository repo;

    @InjectMocks
    private DiscussionService service=new DiscussionServiceRepoImpl();

    private User user;

    private News news;

    private Discussion discussion;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        discussion.setId(1L);
    }

    @Test
    void create() {
        service.create(user,news);
        Mockito.verify(repo,times(1)).save(any());
    }

    @Test
    void readByUser() {
        service.readByUser(user);
        Mockito.verify(repo,times(1)).findByUserId(any());
    }

    @Test
    void delete() {
        service.delete(1L);
        Mockito.verify(repo,times(1)).deleteById(1L);
    }

    @Test
    void findAll() {
        List<Discussion> list=new ArrayList<>();
        list.add(discussion);
        Page<Discussion> page=new PageImpl<Discussion>(list);
        when(repo.findAll(PageRequest.of(0,10, Sort.by("id").descending())))
                .thenReturn(page);
        service.findAll(0,10);
        Mockito.verify(repo,times(1))
                .findAll(PageRequest.of(0,10, Sort.by("id").descending()));
    }

    @Test
    void count() {
        service.count();
        Mockito.verify(repo,times(1)).count();
    }
}