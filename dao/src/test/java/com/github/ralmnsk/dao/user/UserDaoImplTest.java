package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserDaoImplTest {

    public User userInTestCreate(){
        Properties properties=new Properties();
        try {
            properties
                    .load(getClass()
                            .getClassLoader()
                            .getResourceAsStream("daotest.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user=new User(properties.getProperty("user"),
                properties.getProperty("password"),
                new Timestamp(new java.util.Date().getTime()),
                "usr");
        return user;
    }


    public void createUser() {
        User user=userInTestCreate();
        UserDao userDao=new UserDaoImpl();
        userDao.createUser(user);
    }


    public void readUser() {
        User user=userInTestCreate();
        System.out.println(user);

        UserDao newUserDao=new UserDaoImpl();
        User newUser=newUserDao.readUser(user);
        System.out.println("read User:"+newUser.toString());

        assertNotNull(newUser);
        assertEquals(newUser.getName(),user.getName());
        assertEquals(newUser.getPass(),user.getPass());
    }


    public void updateUser() {
        User user=userInTestCreate();
        User userChanging=new User(user.getName(),
                "1234567",
                new Timestamp(new java.util.Date().getTime()),
                "user");
        UserDao userDao=new UserDaoImpl();
        userDao.updateUser(userChanging);
    }


    public void deleteUser() {
        User user=userInTestCreate();
        UserDao userDao=new UserDaoImpl();
        userDao.deleteUser(user);
    }

    @Test
    public void UserDaoImplTest(){
        createUser();
        readUser();
        getById();
        updateUser();
        deleteUser();
    }


    @Test
    public void getById() {
        Long id=29L;
        User user=new User();
        UserDao userDao=new UserDaoImpl();
        user=userDao.getById(id);
        System.out.println(user);
    }
}