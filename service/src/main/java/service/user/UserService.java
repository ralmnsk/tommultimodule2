package service.user;


import dao.user.UserDao;
import model.user.User;

public interface UserService {
    void createUser(User user);
    User readUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void setUserDao(UserDao userDao);
}
