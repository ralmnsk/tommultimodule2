package com.github.ralmnsk.service.user;


import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserService {
    void createUser(UserDto userDto);
    UserDto readUser(UserDto userDto);
    UserDto getById(Long id);
    void updateUser(UserDto userDto);
    void deleteUser(UserDto userDto);

}
