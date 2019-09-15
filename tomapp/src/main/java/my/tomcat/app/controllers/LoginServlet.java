package my.tomcat.app.controllers;

import my.tomcat.app.DBEmul.DBemulation;
import my.tomcat.app.clienttype.ClientType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login =req.getParameter("login");
        String password=req.getParameter("password");
        System.out.println(login+" "+password);
        DBemulation db=new DBemulation();
        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");
        if((login!=null)&&(login.equals(db.getLogin())&&(password.equals(db.getPassword())))){

                session.setAttribute("login",login);
                session.setAttribute("password",password);
                session.setAttribute("userType", setClientType(db.getRole()));
                System.out.println("role" +db.getRole());
                req.getRequestDispatcher("/welcome.jsp").forward(req, resp);

        } else {
            if(login!=null)req.setAttribute("errorLoginPassMessage","Incorrect login or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    private ClientType setClientType(String role) {
        ClientType type=ClientType.GUEST;
        switch (role){
            case "user":type=ClientType.USER;
            break;
            case "admin":type=ClientType.ADMIN;
            break;
        }
        return type;
    }
}
