package com.github.ralmnsk.service.authorization;

import com.github.ralmnsk.model.role.ClientType;
import com.github.ralmnsk.model.user.User;


public interface Authorization {
    ClientType getClientType();

    User getUserInLoginServlet();

    boolean process(String login, String password);
}
