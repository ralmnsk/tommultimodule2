package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;


import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class UserServiceRepoImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceRepoImpl userService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUser() {
        UserDto userDto=new UserDto("testUser","123",new Date(),"usr");
        ModelMapper mapper=new ModelMapper();
        User user=mapper.map(userDto,User.class);
//        doNothing().when(userRepository.save(user));
        userService.createUser(userDto);
        Mockito.verify(userRepository,times(1)).save(user);
    }

    @Test
    void readUser() {
        UserDto userDto=new UserDto(1L,"testUser","123",new Date(),"usr");
        ModelMapper mapper=new ModelMapper();
        User user=mapper.map(userDto,User.class);
        userService.readUser(userDto);
        Mockito.verify(userRepository,times(1)).getOne(user.getId());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getById() {
    }
}