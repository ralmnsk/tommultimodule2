package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import static org.junit.Assert.*;

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
                new Date(new java.util.Date().getTime()),
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
                new Date(new java.util.Date().getTime()),
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
        updateUser();
        deleteUser();
    }

}