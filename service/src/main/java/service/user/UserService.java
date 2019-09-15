package service.user;


import model.user.User;

public interface UserService {
    void createUser(User user);
    void readUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
