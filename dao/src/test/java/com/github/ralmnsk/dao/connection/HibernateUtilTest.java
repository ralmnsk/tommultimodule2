package com.github.ralmnsk.dao.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {

    @Test
    void getEntityManager() {
        assertNotNull(HibernateUtil.getEntityManager());
    }

    @Test
    void getSession() {
        assertNotNull(HibernateUtil.getSession());
    }

    @Test
    void close() {
    }
}