package com.github.ralmnsk.service.user;


import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@Transactional
@ComponentScan(basePackages = {"com.github.ralmnsk.model","com.github.ralmnsk.dao"})
public class UserServiceRepoImpl implements UserService{

//    @PersistenceContext
//    private EntityManager em;

    private UserRepository userRepo; //= UserDaoHiberImpl.getInstance();

    @Autowired
    public UserServiceRepoImpl(UserRepository userRepo) {
        this.userRepo=userRepo;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }
    @Override
    public User readUser(User user) {
        return userRepo.findByName(user.getName());
    }
    @Override
    public void updateUser(User user) {
        userRepo.saveAndFlush(user);
    }
    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> byId = userRepo.findById(id);
        return byId.get();
    }
}
