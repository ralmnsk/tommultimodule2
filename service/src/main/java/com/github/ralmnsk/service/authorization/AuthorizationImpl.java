package com.github.ralmnsk.service.authorization;


import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.clienttype.ClientType;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class
AuthorizationImpl implements Authorization {
    private static Logger logger= LoggerFactory.getLogger(AuthorizationImpl.class);
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

        //UserDao userDao=new UserDaoImpl();
        UserService userService=UserServiceImpl.getInstance();
        //userService.setUserDao(userDao);

        User readUser=userService.readUser(user);
       // System.out.println("checkout user:"+readUser);

        if((readUser!=null)&&(login!=null)&&(login.equals(readUser.getName())&&(password.equals(readUser.getPass())))){
            userInLoginServlet=readUser;
            clientType=setClientType(readUser.getRole());
            return true;
        }
        logger.info(this.getClass()+" process()");
        return false;
    }


    private ClientType setClientType(String role) {
        ClientType type=ClientType.GUEST;
        switch (role){
            case "usr":type=ClientType.USER;
                break;
            case "admin":type=ClientType.ADMIN;
                break;
        }

        return type;
    }
}
