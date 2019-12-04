package com.github.ralmnsk.service.registration;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Registration implements Register{

    @Autowired
    private UserService userService;

    @Override
    public boolean isRegistered(UserDto userDto) {
        UserDto checkUser=userService.readUser(userDto);
        if(checkUser!=null){
            return true;
        }
        return false;
    }
}
