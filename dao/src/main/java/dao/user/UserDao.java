package dao.user;

import model.user.User;

public interface UserDao {
    void createUser(User user);
    void readUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
