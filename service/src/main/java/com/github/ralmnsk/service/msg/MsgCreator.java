package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.model.msg.Msg;

public interface MsgCreator {
    Msg create();
    void getMsgList();
}
