package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscussionDaoHiberImplTest {
//    private DiscussionDao discussionDao=DiscussionDaoHiberImpl.getInstance();
//    private UserDao userDao=UserDaoHiberImpl.getInstance();
//    private NewsDao newsDao=NewsDaoHiberImpl.getInstance();
//    private User user;
//    private News news;
//    private Discussion discussion;
//
//    @BeforeEach
//    void setUp() {
//        user=new User("testUser","testPassword",new Date(),"usr");
//        userDao.createUser(user);
//
//        news=new News("nameNews","dataNews",new Date());
//        news.setUser(user);
//        newsDao.createNews(news);
//
//        discussionDao.create(user,news);
//
//        List<Discussion> list=discussionDao.readByUser(user);
//        list.stream().filter(d->d.getNews().getIdNews()==news.getIdNews());
//        discussion=list.get(0);
//    }
//
//    @AfterEach
//    void tearDown() {
//        userDao.deleteUser(user);
//    }
//
//    @Test
//    void getInstance() {
//        assertNotNull(discussionDao);
//        discussionDao.delete(discussion.getId());
//    }
//
//    @Test
//    void create() {
//        News readNews=newsDao.getById(news.getIdNews());
//        assertTrue(readNews.getDiscussion().getId()==discussion.getId());
//        discussionDao.delete(discussion.getId());
//    }
//
//    @Test
//    void readByUser() {
//        boolean isReadDiscussion=false;
//        List<Discussion> discussions = discussionDao.readByUser(user);
//        News readNews=newsDao.getById(news.getIdNews());
//        Long discussionId=readNews.getDiscussion().getId();
//        for(Discussion d:discussions){
//            if(d.getId()==discussionId){
//                isReadDiscussion=true;
//            }
//        }
//        assertTrue(isReadDiscussion);
//    }
//
//    @Test
//    void delete() {
//        Long discussionId=discussion.getId();
//        discussionDao.delete(discussionId);
//        List<Discussion> list=discussionDao.readByUser(user);
//        assertTrue(list.size()==0);
//    }
//
//    @Test
//    void findAll() {
//        List<Discussion> list=discussionDao.readByUser(user);
//        assertTrue(list.size()>0);
//    }
//
//    @Test
//    void addUserInDiscussion() {
//        User testUser=new User("userInDiscussion","userInDiscussion",new Date(),"usr");
//        userDao.createUser(testUser);
//        discussionDao.addUserInDiscussion(testUser,discussion);
//        List<Discussion> discussions = discussionDao.readByUser(testUser);
//        assertTrue(discussions.size()==1);
//}


//    @Test
//    void findAll() {
//        for (int i=1;i<4;i++){
//
//            UserDao userDao= UserDaoHiberImpl.getInstance();
//            User user=new User("testName"+i,"testPassword"+i,new Date(),"usr");
//            userDao.createUser(user);
//            News news=new News("nameNews"+i,"dataNews"+i,new Date());
//            news.setUser(user);
//            user.addNews(news);
//            NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//            newsDao.createNews(news);
//
//            Discussion discussion=new Discussion();
//            discussion.setNews(news);
//            discussion.getUserSet().add(user);
//            user.getDiscussionSet().add(discussion);
//            discussionDao.create(user,news);
//            userDao.updateUser(user);
//        }
//
//        List<Discussion> discussions=discussionDao.findAll(0,100);
//        assertTrue(discussions.size()>=3);
//    }
//
//    @Test
//    public void createDiscussionInUserAndInNews(){
//        UserDao userDao=UserDaoHiberImpl.getInstance();
//        User user=userDao.getById(1L);
//        NewsDao newsDao=NewsDaoHiberImpl.getInstance();
//        News news=newsDao.getById(39L);
//        Discussion discussion=new Discussion();
//        discussionDao.create(user,news);
//    }
//
//    @Test
//    public void readByUserId(){
//        UserDao userDao=UserDaoHiberImpl.getInstance();
//        User user=userDao.getById(1L);
//        List<Discussion> list=discussionDao.readByUser(user);
//        list.stream().map(d->d.getNews().getNameNews()).forEach(System.out::println);
//    }
//
//    @Test
//    public void deleteDiscussion(){
//        discussionDao.delete(33L);
//    }
}