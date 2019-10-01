package com.github.ralmnsk.service.news.generator;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import java.sql.Timestamp;

public class Generator {

    private UserDao userDao;
    private UserService userService;
    private News news;

    public void generateUserAndNews(){
        userDao=new UserDaoImpl();
        userService.setUserDao(userDao);
        for(int i=0;i<3;i++){
            User user=new User("user"+i,"123",new Timestamp(new java.util.Date().getTime()),"usr");
            userService.createUser(user);
                //--
        }

    }
}
