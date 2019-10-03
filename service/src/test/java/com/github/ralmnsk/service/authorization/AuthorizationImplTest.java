package com.github.ralmnsk.service.authorization;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AuthorizationImplTest {

    @Test
    public void process() {
        Authorization authorization;
        AuthorizationImpl authorizationImpl;
        String username = "someusername";
        String password = "unsafePassword";
        authorization = Mockito.mock(Authorization.class);
        authorizationImpl = new AuthorizationImpl();
        when(authorization.process(username, password))
                .thenReturn(true);
        boolean actual = authorizationImpl.process(username, password);
        assertFalse(actual);

    }
}