package my.tomcat.app.controllers;

import my.tomcat.app.clienttype.ClientType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("registration",null);
        req.getRequestDispatcher("/reg_succsses.jsp").forward(req, resp);
    }

//    private ClientType setClientType(String role) {
//        ClientType type=ClientType.GUEST;
//        switch (role){
//            case "user":type=ClientType.USER;
//            break;
//            case "admin":type=ClientType.ADMIN;
//            break;
//        }
//        return type;
//    }
}
