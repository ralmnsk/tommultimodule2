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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;

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

//    @Test
//    void userServiceTest(){
//        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
//        userServiceTest = ctx.getBean(UserService.class);
//        assertNotNull(userServiceTest);
//    }

    @Test
    void createUser() {
        User user=new User("testUser","123",new Date(),"usr");
//        doNothing().when(userRepository.save(user));
        userService.createUser(user);
        Mockito.verify(userRepository,times(1)).save(user);
    }

    @Test
    void findAllNewsByUserId(){

    }
}