package com.github.ralmnsk.dao.news;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsDaoCounterImplTest {
    private NewsDaoCounter newsDaoCounter=NewsDaoCounterImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(newsDaoCounter);
    }

    @Test
    void getNewsCount() {
        System.out.println(newsDaoCounter.getNewsCount());
        assertTrue(newsDaoCounter.getNewsCount()>0);
    }
}
