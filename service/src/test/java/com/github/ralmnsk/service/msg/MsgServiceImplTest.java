package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.model.msg.Msg;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MsgServiceImplTest {

    @Test
    void getInstance() {
        assertNotNull(MsgServiceImpl.getInstance());
    }

    @Test
    void create() {
        MsgServiceImpl msgService=mock(MsgServiceImpl.class);
        Msg msg=new Msg(new Date(),"testMessage");
        msgService.create(msg);
        verify(msgService,times(1)).create(msg);
    }

    @Test
    void read() {
        MsgServiceImpl msgService=mock(MsgServiceImpl.class);
        Msg msg=new Msg(new Date(),"testMessage");
        msgService.read(1L);
        verify(msgService,times(1)).read(1L);
        when(msgService.read(1L)).thenReturn(msg);
        assertTrue(msgService.read(1L).getText().equals("testMessage"));
    }

    @Test
    void update() {
        MsgServiceImpl msgService=mock(MsgServiceImpl.class);
        msgService.update(1L,"newText");
        verify(msgService,times(1)).update(1L,"newText");
    }

    @Test
    void delete() {
        MsgServiceImpl msgService=mock(MsgServiceImpl.class);
        msgService.delete(1L);
        verify(msgService,times(1)).delete(1L);
    }

    @Test
    void findAll() {
        List<Msg> list=new ArrayList<>();
        for (int i=0;i<11;i++){
            Msg msg=new Msg(new Date(),"testMessage");
            list.add(msg);
        }

        MsgServiceImpl msgService=mock(MsgServiceImpl.class);
        msgService.findAll(0,10);
        verify(msgService,times(1)).findAll(0,10);

        when(msgService.findAll(0,10)).thenReturn(list);
        assertTrue(msgService.findAll(0,10).size()>9);
    }

    @Test
    void create1() {
        MsgService msgService=MsgServiceImpl.getInstance();
        Msg msg=new Msg(new Date(),"testMessage");
        msgService.create(msg);

        Msg readMsg=msgService.read(msg.getId());
        assertEquals(msg.getId(),readMsg.getId());

        msgService.update(readMsg.getId(),"testMessage2");
        Msg updateMsg=msgService.read(readMsg.getId());
        assertEquals(updateMsg.getText(),"testMessage2");

        List<Msg> list=msgService.findAll(0,10);
        assertTrue(list.size()>0);

        msgService.delete(readMsg.getId());
    }

}