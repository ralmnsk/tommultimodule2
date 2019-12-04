package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dto.MsgDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.Map;

public interface MsgCreator {
    MsgDto create();
    Map<MsgDto, UserDto> getMsgMap();
    void setDiscussNewsId(Long discussNewsId);
    void setMsgText(String msgText);
    void setUserDto(UserDto userDto);

}
