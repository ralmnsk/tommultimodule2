package my.tomcat.app.controllers;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.user.User;
import my.tomcat.app.DBEmul.DBemulation;
import my.tomcat.app.clienttype.ClientType;
import service.user.UserService;
import service.user.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String password=req.getParameter("password");
        System.out.println(login+" "+password);
        //DBemulation db=new DBemulation();
        User user=new User();
        user.setName(login);
        user.setPass(password);

        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User readUser=userService.readUser(user);

        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");
        if((login!=null)&&(login.equals(readUser.getName())&&(password.equals(readUser.getPass())))){

//                session.setAttribute("login",login);
//                session.setAttribute("password",password);
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
