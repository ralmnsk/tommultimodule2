package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UserDaoHiberImplTest {
    private UserDao userDao=UserDaoHiberImpl.getInstance();

    public User userInTestCreate(){
        User user=new User("testName","testPassword",new Timestamp(new java.util.Date().getTime()),"usr");
        return user;
    }

    @Test
    public void createUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        System.out.println(testUser);
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getPass(),testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void readUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getPass(),testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void updateUser() {
        User user=userInTestCreate();
        userDao.createUser(user);

        User updateUser=userDao.readUser(user);
        updateUser.setPass("ChangedPass");
        System.out.println(updateUser);
        userDao.updateUser(updateUser);
        User testUser=userDao.readUser(updateUser);
        assertEquals("ChangedPass",testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void deleteUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User readUser=userDao.readUser(user);
//        System.out.println("Testing: "+readUser);
        userDao.deleteUser(readUser);
        User afterTestUser=userDao.readUser(user);
        assertNull(afterTestUser);
    }

    @Test
    public void getById() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        Long userId=testUser.getId();
        User userById=userDao.getById(userId);
        assertEquals(testUser.getName(),userById.getName());
        assertEquals(testUser.getPass(),userById.getPass());
        userDao.deleteUser(userById);
    }
}