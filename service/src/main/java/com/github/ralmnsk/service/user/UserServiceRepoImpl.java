package com.github.ralmnsk.service.user;


import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper mapper;

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
    public void createUser(UserDto userDto) {
        User user=mapper.map(userDto,User.class);
        userRepo.save(user);
    }
    @Override
    public UserDto readUser(UserDto userDto) {
        User user=mapper.map(userDto,User.class);
        User readUser=userRepo.findByName(user.getName());
        UserDto readUserDto=mapper.map(readUser,UserDto.class);
        return readUserDto;
    }
    @Override
    public void updateUser(UserDto userDto) {
        User user=mapper.map(userDto,User.class);
        userRepo.saveAndFlush(user);
    }
    @Override
    public void deleteUser(UserDto userDto) {
        User user=mapper.map(userDto,User.class);
        userRepo.delete(user);
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> byId = userRepo.findById(id);
        User user=byId.get();
        if (user!=null){
            UserDto userDto=mapper.map(user,UserDto.class);
            return userDto;
        }
        return null;
    }

}
