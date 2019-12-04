package com.github.ralmnsk.demo.security;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.role.ClientType;
import com.github.ralmnsk.model.user.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@Data
public class RegistrationForm {

  private String login;
  private String password;

  
  public UserDto toUser(PasswordEncoder passwordEncoder) {
    return new UserDto(
        login, passwordEncoder.encode(password),new Date(),"ROLE_"+ClientType.USER.name());
  }

  public UserDto toUserNoPassEncoder() {
    return new UserDto(
            login, password,new Date(),"ROLE_"+ClientType.USER.name());
  }
  
}
