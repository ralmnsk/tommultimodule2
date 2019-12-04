package com.github.ralmnsk.demo.security;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;

import com.github.ralmnsk.service.registration.Register;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Controller
public class RegistrationController {
  @Autowired
  private Register register;
  @Autowired
  private ModelMapper mapper;

  private UserService userService;
  private PasswordEncoder passwordEncoder;


  public RegistrationController(
      UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping("/goregistrate")
  public String registerForm(HttpServletRequest req,
                             Locale locale) {
    if(req.getSession().getAttribute("errorLoginPassMessage")!=null){
      req.getSession().removeAttribute("errorLoginPassMessage");
    }
    return "registration";
  }
  
  @PostMapping("/registration")
  public String processRegistration(RegistrationForm form,
                                    HttpServletRequest req,
                                    Locale locale) {

    String page;
    UserDto registrationUserDto=form.toUser(passwordEncoder);

    boolean isRegisteredUser=register.isRegistered(registrationUserDto);
    if(isRegisteredUser){
      String errorRegistrationMessage="Пользователь с таким login уже зарегистрирован";
      req.getSession().setAttribute("errorRegistrationMessage",errorRegistrationMessage);
      return "redirect:/goregistrate";
    }
    userService.createUser(registrationUserDto);
    return "redirect:/login";
  }

}
