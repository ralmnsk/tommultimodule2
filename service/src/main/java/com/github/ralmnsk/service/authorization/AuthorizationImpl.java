package com.github.ralmnsk.service.authorization;



import com.github.ralmnsk.model.role.ClientType;
import com.github.ralmnsk.model.user.User;

import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//@Slf4j
//@Service
public class
AuthorizationImpl implements Authorization {
//    private static Logger logger= LoggerFactory.getLogger(AuthorizationImpl.class);
//    @Autowired
    private UserService userService;

//    @Autowired
    private PasswordEncoder encoder;

    private ClientType clientType;
    private User userInLoginServlet;

    @Override
    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public User getUserInLoginServlet() {
        return userInLoginServlet;
    }

    @Override
    public boolean process(String login, String password) {
        User user=new User();
        user.setName(login);
        user.setPass(password);

        User readUser=userService.readUser(user);

        if((readUser!=null)&&(login!=null)&&(login.equals(readUser.getName()))){

            boolean isPasswordMatches = encoder.matches(password,readUser.getPass());
//            System.out.println("LOOK:"+isPasswordMatches);
            //readUser.setPass(encode);

            if (isPasswordMatches){
                userInLoginServlet=readUser;
                clientType=setClientType(readUser.getRole());
                return true;
            }
        }
//        logger.info(this.getClass()+" process()");
        return false;
    }


    private ClientType setClientType(String role) {
        ClientType type=ClientType.GUEST;
        switch (role){
            case "USER":type=ClientType.USER;
                break;
            case "ADMIN":type=ClientType.ADMIN;
                break;
        }

        return type;
    }
}
