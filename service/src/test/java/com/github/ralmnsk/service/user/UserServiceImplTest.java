package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    @Test
    void createUser1Test() {
        User user = getMeTestUser();
        UserDao userDao=mock(UserDao.class);
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);
        when(userDao.readUser(user)).thenReturn(user);
        userService.createUser(user);
        assertEquals("Apple333", userService.readUser(user).getName());
        verify(userDao).createUser(user);
    }

    private User getMeTestUser() {
        User user = new User();
        user.setId(1000L);
        user.setName("Apple333");
        user.setPass("pass333");
        user.setJoinDate(new Timestamp(new java.util.Date().getTime()));
        user.setRole("usr");
        return user;
    }

    @Test
    void getInstance1() {
        assertNotNull(UserServiceImpl.getInstance());
    }


    @Test
    void readUser1() {
        UserDao userDao=mock(UserDao.class);
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User user = getMeTestUser();
        when(userDao.readUser(user)).thenReturn(user);

        assertEquals("Apple333", userService.readUser(user).getName());
    }

    @Test
    void updateUser1() {
        UserDao userDao=mock(UserDao.class);
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User user = getMeTestUser();
        user.setPass("pass222");

        userService.updateUser(user);
        doNothing().when(userDao).updateUser(user);
        verify(userDao).updateUser(user);
    }

    @Test
    void deleteUser1() {
        UserDao userDao=mock(UserDao.class);
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User user = getMeTestUser();
        userService.deleteUser(user);


        doNothing().when(userDao).deleteUser(user);
        verify(userDao).deleteUser(user);
    }

    @Test
    void getById1() {
        UserDao userDao=mock(UserDao.class);
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User user = getMeTestUser();
        User testUser = userService.getById(1000L);
        when(userDao.getById(1000L)).thenReturn(user);
        assertEquals("Apple333", userService.getById(1000L).getName());
    }
}