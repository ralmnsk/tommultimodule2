package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    void findByName() {
        User user=new User("testUser","123",new Date(),"usr");
        userRepo.save(user);
        User readUser=userRepo.findByName("testUser");
        System.out.println(readUser);
        assertTrue(user.getName().equals(readUser.getName()));

        News news=new News("testNewsName","testNewsData",new Date());
        news.setUser(user);
        newsRepo.save(news);

        News readNews=newsRepo.findById(news.getIdNews()).get();
        assertTrue(news.getNameNews().equals(news.getNameNews()));
//        System.out.println(readNews);

        newsRepo.delete(readNews);
        userRepo.delete(user);
    }

    @Test
    void countAllNews() {
        News news=new News("testNewsName","testNewsData",new Date());
        newsRepo.save(news);
        Long n=newsRepo.countAllNews();
        assertTrue(n>0);
        newsRepo.delete(news);

    }

    @Test
    void findAllNewsByUserId(){
        User user=new User("testUser","123",new Date(),"usr");
        userRepo.save(user);

        News news=new News("testNewsName","testNewsData",new Date());
        news.setUser(user);
        newsRepo.save(news);
        List<Long> list = newsRepo.findAllNewsByUserId(user.getId());
//        list.stream().forEach(System.out::println);
        assertTrue(list.size()>0);
        Long newsId=list.get(0);
        News testNews=newsRepo.getOne(newsId);
        assertTrue(testNews.getIdNews().equals(news.getIdNews()));

        newsRepo.delete(news);
        userRepo.delete(user);
    }
}