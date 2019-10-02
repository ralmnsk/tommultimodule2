package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;

public interface UserDao {
    void createUser(User user);
    User readUser(User user);
    User getById(Long id);
    void updateUser(User user);
    void deleteUser(User user);
}
