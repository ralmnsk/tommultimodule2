package com.github.ralmnsk.demo.security;

import com.github.ralmnsk.model.role.ClientType;
import com.github.ralmnsk.model.user.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@Data
public class RegistrationForm {

  private String login;
  private String password;

  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        login, passwordEncoder.encode(password),new Date(),"ROLE_"+ClientType.USER.name());
  }

  public User toUserNoPassEncoder() {
    return new User(
            login, password,new Date(),"ROLE_"+ClientType.USER.name());
  }
  
}
