package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Test
    void findByName() {
        User user=new User("testUser","123",new Date(),"usr");
        userRepo.save(user);
        User readUser=userRepo.findByName("testUser");
        System.out.println(readUser);
        assertTrue(user.getName().equals(readUser.getName()));
//        userRepo.delete(user);
    }
}