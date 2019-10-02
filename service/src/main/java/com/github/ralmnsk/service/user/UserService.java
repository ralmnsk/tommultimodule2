package com.github.ralmnsk.service.user;


import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.model.user.User;

public interface UserService {
    void createUser(User user);
    User readUser(User user);
    User getById(Long id);
    void updateUser(User user);
    void deleteUser(User user);
    void setUserDao(UserDao userDao);
}
