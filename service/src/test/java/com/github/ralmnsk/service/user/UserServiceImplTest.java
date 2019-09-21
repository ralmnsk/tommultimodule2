package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.user.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {
    @InjectMocks private UserServiceImpl userService;
    @Mock private UserDaoImpl userDao;

    @Test
    public void setUser() {
    }

    @Test
    public void setUserDao() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void readUser() {
        initMocks(this);
        User user=new User("Apple","pass",new Date(new java.util.Date().getTime()),"usr");
        user.setId(10L);
        when(userDao.readUser(user)).thenReturn(getMeTestUser());
        User testUser = userService.readUser(user);
        assertNotNull(testUser);
        assertEquals("Apple", testUser.getName());
        assertEquals("pass", user.getPass());
    }

    private User getMeTestUser() {
        User user=new User();
        user.setId(10L);
        user.setName("Apple");
        user.setPass("pass");
        user.setJoinDate(new Date(new java.util.Date().getTime()));
        user.setRole("usr");
        return user;
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }
}