package com.github.ralmnsk.service.authorization;


import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.clienttype.ClientType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorizationImplTest {

    @Test
    private void process() {
        Authorization authorization;
        AuthorizationImpl authorizationImpl;
        String username = "someusername";
        String password = "unsafePassword";
        authorization = mock(Authorization.class);
        authorizationImpl = new AuthorizationImpl();
        when(authorization.process(username, password))
                .thenReturn(true);
        boolean actual = authorizationImpl.process(username, password);
        assertFalse(actual);
    }

    @Test
    void getClientType() {
        AuthorizationImpl authorization=mock(AuthorizationImpl.class);
        when(authorization.getClientType()).thenReturn(ClientType.USER);
        assertTrue(authorization.getClientType()==ClientType.USER);
    }

    @Test
    void getUserInLoginServlet() {
        User user=new User("user","password",new Date(),"usr");
        AuthorizationImpl authorization=mock(AuthorizationImpl.class);
        when(authorization.getUserInLoginServlet()).thenReturn(user);
        assertTrue(authorization.getUserInLoginServlet().getName().equals(user.getName()));
    }

    @Test
    void setClientTypeTest(){
        AuthorizationImpl authorization=new AuthorizationImpl();
        Class c = authorization.getClass();
        try {
            Method m = c.getMethod("setClientType",String.class);
            ClientType clientType =(ClientType)m.invoke(authorization, "usr");
            assertTrue(authorization.getClientType().equals(ClientType.USER));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}