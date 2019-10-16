package com.github.ralmnsk.dao.msg;

import com.github.ralmnsk.model.msg.Msg;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MsgDaoHiberImplTest {
    private MsgDao msgDao=MsgDaoHiberImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(msgDao);
    }

    @Test
    void create() {
        Msg msg=new Msg(new Date(), "testMessage");
        msgDao.create(msg);
        assertEquals(msg.getText(),msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());
    }

    @Test
    void read() {
        Msg msg=new Msg(new Date(), "testMessage");
        msgDao.create(msg);
        assertEquals(msg.getText(),msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());
    }

    @Test
    void update() {
        Msg msg=new Msg(new Date(), "testMessage");
        msgDao.create(msg);
        msgDao.update(msg.getId(),"new test text");
        assertEquals("new test text",msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());
    }

    @Test
    void delete() {
        Msg msg=new Msg(new Date(), "testMessage");
        msgDao.create(msg);
        msgDao.delete(msg.getId());
        assertNull(msgDao.read(msg.getId()));
    }

    @Test
    void findAll() {
        for (int i=0;i<10;i++){
            Msg msg=new Msg(new Date(), "testMessage");
            msgDao.create(msg);
        }
        List<Msg> msgList=msgDao.findAll(0,11);
        assertTrue(msgList.size()>9);
        msgList.stream().forEach(m->msgDao.delete(m.getId()));
    }
}