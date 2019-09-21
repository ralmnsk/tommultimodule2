package com.github.ralmnsk.app.filters;

import com.github.ralmnsk.service.clienttype.ClientType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter({"/topsecret","/topsecret2"})
public class TopSecretFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.print("filter is invoked before");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (authorization(request, response)) return;
        chain.doFilter(request, response);
        out.print("filter is invoked after");
    }

    private boolean authorization(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;
        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");

        if (clientType!=ClientType.ADMIN){

            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/site/inform");
            dispatcher.forward(req, resp);
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
