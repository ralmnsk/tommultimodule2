package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.Map;

public interface MsgCreator {
    Msg create();
    Map<Msg, User> getMsgMap();
    void setDiscussNewsId(Long discussNewsId);
    void setMsgText(String msgText);
    void setUser(User user);

}
