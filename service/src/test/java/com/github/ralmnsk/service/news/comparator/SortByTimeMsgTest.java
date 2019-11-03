package com.github.ralmnsk.service.news.comparator;

import com.github.ralmnsk.model.msg.Msg;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class SortByTimeMsgTest {

    @Test
    void compare() {
        Msg one=new Msg(new Date(),"msg");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Msg two=new Msg(new Date(),"msg");

        SortByTimeMsg sortByTimeMsg=new SortByTimeMsg();

        assertTrue(sortByTimeMsg.compare(one,two)==-1);
        assertTrue(sortByTimeMsg.compare(one,one)==0);
        assertTrue(sortByTimeMsg.compare(two,one)==1);
    }
}