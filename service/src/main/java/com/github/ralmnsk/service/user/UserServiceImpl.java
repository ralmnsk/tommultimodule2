package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.model.user.User;

public class UserServiceImpl implements UserService{

    private User user;
    private UserDao userDao;

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public User readUser(User user) {
        return userDao.readUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
