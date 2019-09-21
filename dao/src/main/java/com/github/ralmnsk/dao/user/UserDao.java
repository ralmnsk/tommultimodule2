package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;

public interface UserDao {
    void createUser(User user);
    User readUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
