package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NewsDeleterImplTest {

    @Test
    void delete() {
        News news=new News("testName","testData",new Date());
        User user=new User ("testUser","testPass",new Date(),"user");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        userDao.createUser(user);

        news.setUser(user);
        newsDao.createNews(news);
        Long idNews=newsDao.readNews(news).getIdNews();
        assertEquals(news.getNameNews(),newsDao.readNews(news).getNameNews());
        NewsDeleter newsDeleter=new NewsDeleterImpl(news,user);
        newsDeleter.delete();
        userDao.deleteUser(user);
        assertNull(newsDao.getById(idNews));
    }
}