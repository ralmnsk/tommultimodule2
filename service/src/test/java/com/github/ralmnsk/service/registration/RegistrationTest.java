package com.github.ralmnsk.service.registration;

import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class RegistrationTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private Register register=new Registration();

    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");

    }

    @Test
    void isRegistered() {

    }
}