package com.github.ralmnsk.service.security;

import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserRepositoryUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepositoryUserDetailsService userDetailService=new UserRepositoryUserDetailsService(userRepository);

    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"testUser","123",new Date(),"ROLE_USER");

    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByName(user.getName())).thenReturn(user);
        userDetailService.loadUserByUsername(user.getName());
        Mockito.verify(userRepository,times(1)).findByName(any());
    }

    @Test
    void loadUserByUsernameNull(){
        when(userRepository.findByName(user.getName())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class,()->{
            userDetailService.loadUserByUsername(user.getName());
        });
    }
}