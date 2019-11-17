package com.github.ralmnsk.service.clienttype;

import com.github.ralmnsk.model.role.ClientType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTypeTest {

    @Test
    private void testClientType(){
        ClientType admin = ClientType.ADMIN;
        assertTrue(admin.equals(ClientType.ADMIN));
    }
}