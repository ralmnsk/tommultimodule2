package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        User user=new User("Apple","pass",new Timestamp(new java.util.Date().getTime()),"usr");
        user.setId(10L);
        when(userDao.readUser(user)).thenReturn(getMeTestUser());
        User testUser = userService.readUser(user);
        assertNotNull(testUser);
        assertEquals("Apple", testUser.getName());
        assertEquals("pass", user.getPass());
    }

    private User getMeTestUser() {
        User user=new User();
        user.setId(1000L);
        user.setName("Apple");
        user.setPass("pass");
        user.setJoinDate(new Timestamp(new java.util.Date().getTime()));
        user.setRole("usr");
        return user;
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void getById() {
        UserDao userDao1=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        userService.setUserDao(userDao1);
        User user=userService.getById(29L);

        System.out.println(user);
    }
}