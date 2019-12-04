package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class NewsDeleterImplTest {
    @Autowired
    private NewsDeleter newsDeleter;
    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsService newsService;

    @Test
    void delete() {

        News news=new News("testName","testData",new Date());
        User user=new User ("testUser","testPass",new Date(),"ROLE_USER");

//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        userDao.createUser(user);
//
//        news.setUser(user);
//        newsDao.createNews(news);
//        Long idNews=newsDao.readNews(news).getIdNews();
//        assertEquals(news.getNameNews(),newsDao.readNews(news).getNameNews());
//        NewsDeleter newsDeleter=new NewsDeleterImpl(news,user);
//        newsDeleter.delete();
//        userDao.deleteUser(user);
//        assertNull(newsDao.getById(idNews));
    }
}