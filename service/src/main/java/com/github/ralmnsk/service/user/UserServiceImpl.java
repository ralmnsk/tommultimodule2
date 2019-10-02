package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.model.user.User;

public class UserServiceImpl implements UserService{

    private User user;
    private UserDao userDao;

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }
    @Override
    public User readUser(User user) {
        return userDao.readUser(user);
    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
