package com.example.demo.security;

import com.github.ralmnsk.model.user.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@Data
public class RegistrationForm {

  private String username;
  private String password;

  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        username, passwordEncoder.encode(password),new Date(),"usr");
  }
  
}
