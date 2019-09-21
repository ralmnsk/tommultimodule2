package com.github.ralmnsk.service.authorization;

import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.clienttype.ClientType;

public interface Authorization {
    ClientType getClientType();

    User getUserInLoginServlet();

    boolean process(String login, String password);
}
