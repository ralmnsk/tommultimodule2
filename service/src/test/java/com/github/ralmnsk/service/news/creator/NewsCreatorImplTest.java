package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NewsCreatorImplTest {

//    @Test
//    void newsCreate() {
//        User user=new User("testUser","123",new Date(),"usr");
//        UserService userService= UserServiceImpl.getInstance();
//        userService.createUser(user);
//        NewsCreator newsCreator=new NewsCreatorImpl(user,"testData","testName");
//        newsCreator.newsCreate();
//        NewsService newsService= NewsServiceImpl.getInstance();
//        List<News> list=newsService
//                .findAllNews(0,100)
//                .stream().filter(n->n.getNameNews().equals("testName"))
//                .collect(Collectors.toList());
//        News readNews=list.get(0);
//        assertTrue(readNews.getNameNews().equals("testName"));
//        newsService.deleteNews(readNews);
//
//    }
}