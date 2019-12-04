package com.github.ralmnsk.service.user;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;


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
    @Autowired
    private ModelMapper mapper;
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
        UserDto userDto=new UserDto("testUser","123",new Date(),"usr");
//        doNothing().when(userRepository.save(user));
        User user=mapper.map(userDto,User.class);
        userService.createUser(userDto);
        Mockito.verify(userRepository,times(1)).save(user);
    }

    @Test
    void findAllNewsByUserId(){

    }
}