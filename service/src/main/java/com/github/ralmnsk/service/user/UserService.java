package com.github.ralmnsk.service.user;


import com.github.ralmnsk.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserService {
    void createUser(User user);
    User readUser(User user);
    User getById(Long id);
    void updateUser(User user);
    void deleteUser(User user);

}
