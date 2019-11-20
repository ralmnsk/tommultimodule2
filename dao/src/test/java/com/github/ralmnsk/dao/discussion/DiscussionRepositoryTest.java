package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class DiscussionRepositoryTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private DiscussionRepository discussionRepo;


    @Test
    void findByNewsId() {
        User user=new User("testUser","123",new Date(),"usr");
        userRepo.save(user);

        News news=new News("testNewsName","testNewsData",new Date());
        news.setUser(user);
        newsRepo.save(news);

        Discussion discussion=new Discussion();
        discussion.getUserSet().add(user);
        discussion.setNews(news);
        discussionRepo.save(discussion);

        Discussion readDiscussion=discussionRepo.findByNewsId(news.getIdNews()).get(0);
        assertTrue(discussion.getId()==readDiscussion.getId());

        discussionRepo.delete(discussion);
        newsRepo.delete(news);
        userRepo.delete(user);
    }

    @Test
    void findByUserId() {
        User user=new User("testUser","123",new Date(),"usr");
        userRepo.save(user);

        News news=new News("testNewsName","testNewsData",new Date());
        news.setUser(user);
        newsRepo.save(news);

        Discussion discussion=new Discussion();
        discussion.getUserSet().add(user);
        discussion.setNews(news);
        user.getDiscussionSet().add(discussion);
        news.setDiscussion(discussion);
        discussionRepo.save(discussion);

        Discussion readDiscussion=discussionRepo.findByUserId(user.getId()).get(0);
        assertTrue(discussion.getId()==readDiscussion.getId());

        discussionRepo.delete(discussion);
        newsRepo.delete(news);
        userRepo.delete(user);
    }

//    @Test
//    void findByUserIdAndNewsId(){
//        User user=new User("testUser","123",new Date(),"usr");
//        userRepo.save(user);
//
//        News news=new News("testNewsName","testNewsData",new Date());
//        news.setUser(user);
//        newsRepo.save(news);
//
//        Discussion discussion=new Discussion();
//        discussion.getUserSet().add(user);
//        discussion.setNews(news);
//        user.getDiscussionSet().add(discussion);
//        news.setDiscussion(discussion);
//        discussionRepo.save(discussion);
//
//        Discussion readDiscussion=discussionRepo.findByUserIdAndNewsId(user.getId(),news.getIdNews()).get(0);
//        assertTrue(discussion.getId()==readDiscussion.getId());
//
//        discussionRepo.delete(discussion);
//        newsRepo.delete(news);
//        userRepo.delete(user);
//    }


}