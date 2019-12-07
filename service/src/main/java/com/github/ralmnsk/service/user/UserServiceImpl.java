package com.github.ralmnsk.service.user;

import com.github.ralmnsk.model.user.User;

public class UserServiceImpl implements UserService{

    private User user;
    private UserDao userDao= UserDaoHiberImpl.getInstance();

    private static volatile UserService instance;

    public static UserService getInstance() {
        UserService localInstance = instance;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserServiceImpl();
                }
            }
        }
        return localInstance;
    }


//    @Override
//    public void setUserDao(UserDao userDao){
//        this.userDao=userDao;
//    }
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
