package com.github.ralmnsk.service.user;

import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {


    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() {
        User user=getMeTestUser();
        userService.createUser(user);
        when(userService.readUser(user)).thenReturn(user);
        assertSame("Apple333",userService.readUser(user).getName());
    }

    @Test
    public void readUser() {
        User user=getMeTestUser();
        when(userService.readUser(user)).thenReturn(user);
        assertNotNull(userService.readUser(user));
        assertSame("Apple333",userService.readUser(user).getName());
    }


    private User getMeTestUser() {
        User user=new User();
        user.setId(1000L);
        user.setName("Apple333");
        user.setPass("pass333");
        user.setJoinDate(new Timestamp(new java.util.Date().getTime()));
        user.setRole("usr");
        return user;
    }

    @Test
    public void updateUser() {
        User user=getMeTestUser();
        user.setPass("pass222");
        userService.updateUser(user);
        when(userService.readUser(user)).thenReturn(user);
        assertSame("pass222", userService.readUser(user).getPass());
    }

    @Test
    public void deleteUser() {
        User user=getMeTestUser();
        userService.deleteUser(user);
        when(userService.readUser(user)).thenReturn(null);
        assertNull(userService.readUser(user));
    }

    @Test
    public void getById() {
        User user=getMeTestUser();
        User testUser=userService.getById(1000L);
        when(userService.getById(1000L)).thenReturn(user);
        assertSame("Apple333",userService.getById(1000L).getName());

    }
}