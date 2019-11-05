package com.github.ralmnsk.service.authorization;


import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.clienttype.ClientType;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationImplTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthorizationImpl authorization=new AuthorizationImpl();

//    @Test
//    private void process() {
//        Authorization authorization;
//        AuthorizationImpl authorizationImpl;
//        String username = "someusername";
//        String password = "unsafePassword";
//        authorization = mock(Authorization.class);
//        authorizationImpl = new AuthorizationImpl();
//        when(authorization.process(username, password))
//                .thenReturn(true);
//        boolean actual = authorizationImpl.process(username, password);
//        assertFalse(actual);
//    }

//    @Test
//    void getClientType() {
//        User user=new User("user","password",new Date(),"usr");
//        doNothing().when(userService).readUser(user);
//        verify(authorization).process("user","password");
////        AuthorizationImpl authorization=mock(AuthorizationImpl.class);
////        when(authorization.getClientType()).thenReturn(ClientType.USER);
////        assertTrue(authorization.getClientType()==ClientType.USER);
//    }

//    @Test
//    void getUserInLoginServlet() {
//        User user=new User("user","password",new Date(),"usr");
//        AuthorizationImpl authorization=mock(AuthorizationImpl.class);
//        when(authorization.getUserInLoginServlet()).thenReturn(user);
//        assertTrue(authorization.getUserInLoginServlet().getName().equals(user.getName()));
//    }

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

    @Test
    void process1() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        AuthorizationImpl authorization=new AuthorizationImpl();
        boolean isAuthorized=authorization.process("testUser", "testPassword");
        assertTrue(isAuthorized);

        boolean isAuthorized2=authorization.process("1testUser", "testPassword");
        assertFalse(isAuthorized2);

        User readUser=userDao.readUser(user);
        userDao.deleteUser(readUser);
    }

    @Test
    void getUserInLoginServlet1() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        AuthorizationImpl authorization=new AuthorizationImpl();
        authorization.process("testUser", "testPassword");
        User authUser=authorization.getUserInLoginServlet();
        assertTrue(user.getName().equals(authUser.getName()));
        assertTrue(authorization.getClientType().equals(ClientType.USER));
        User readUser=userDao.readUser(user);
        userDao.deleteUser(readUser);
    }
}