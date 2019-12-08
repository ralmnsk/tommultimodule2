package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
//@ComponentScan({"com.github.ralmnsk.service","com.github.ralmnsk.dao",
//        "com.github.ralmnsk.model", "com.github.ralmnsk.demo"})
//@EnableJpaRepositories
class UserServiceRepoImplTest {
//    private GenericApplicationContext ctx;
//    private UserService userServiceTest;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService=new UserServiceRepoImpl(userRepository);

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUser() {
        User user=new User("testUser","123",new Date(),"usr");
//        doNothing().when(userRepository.save(user));
        userService.createUser(user);
        Mockito.verify(userRepository,times(1)).save(user);
    }

    @Test
    void readUser() {
        User user=new User("testUser","123",new Date(),"usr");
        userService.readUser(user);
        Mockito.verify(userRepository,times(1)).findByName(user.getName());
    }

    @Test
    void updateUser() {
        User user=new User("testUser","123",new Date(),"usr");
        userService.updateUser(user);
        Mockito.verify(userRepository,times(1)).saveAndFlush(user);
    }

    @Test
    void deleteUser() {
        User user=new User("testUser","123",new Date(),"usr");
        userService.deleteUser(user);
        Mockito.verify(userRepository,times(1)).delete(user);
    }

    @Test
    public void getById(){
        User user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        Optional<User> userOptional= Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(userOptional);

        userService.getById(1L);
        Mockito.verify(userRepository,times(1)).findById(1L);
    }

}