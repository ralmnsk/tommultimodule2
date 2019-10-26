package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsDaoCounterForUserImplTest {
    private NewsDaoCounterForUser newsDaoCounterForUser=NewsDaoCounterForUserImpl.getInstance();
    @Test
    void getInstance() {
    assertNotNull(newsDaoCounterForUser);
    }

    @Test
    void getNewsCount() {
        User user= UserDaoHiberImpl.getInstance().getById(1L);
        int count=newsDaoCounterForUser.getNewsCount(user);
        System.out.println(user+" news count:"+count);
        assertTrue(count>0);

    }
}