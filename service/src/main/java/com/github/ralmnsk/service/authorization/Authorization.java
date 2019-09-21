package com.github.ralmnsk.service.authorization;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.clienttype.ClientType;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authorization {

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String password=req.getParameter("password");
        System.out.println(login+" "+password);

        User user=new User();
        user.setName(login);
        user.setPass(password);

        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User readUser=userService.readUser(user);
        System.out.println(readUser);

        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");
        if((login!=null)&&(login.equals(readUser.getName())&&(password.equals(readUser.getPass())))){

            session.setAttribute("user",readUser);
            session.setAttribute("userType", setClientType(readUser.getRole()));
            System.out.println("user "+login+" has role: " +readUser.getRole());
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp);

        } else {
            if(login!=null)req.setAttribute("errorLoginPassMessage","Incorrect login or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
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
