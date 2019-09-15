package my.tomcat.app.filters;

import my.tomcat.app.clienttype.ClientType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter({"/*"})
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintWriter out=response.getWriter();
        out.print("filter is invoked before");

        if (authorization(request, response)) return;

        chain.doFilter(request, response);
        out.print("filter is invoked after");
    }

    private boolean authorization(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;
        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");

        if (clientType==null){
            //clientType=ClientType.GUEST;
            session.setAttribute("userType", clientType);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/login");
            dispatcher.forward(req, resp);
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
