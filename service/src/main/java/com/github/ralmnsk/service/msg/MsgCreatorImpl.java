package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.dto.MsgDto;
import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.news.comparator.SortByTimeMsg;
import com.github.ralmnsk.service.news.editor.NewsEditorImpl;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Service
public class MsgCreatorImpl implements MsgCreator{
    @Autowired
    private MsgService msgService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    private Long discussNewsId;
    private String msgText;
    private UserDto userDto;


    public MsgCreatorImpl() {
    }

    public Long getDiscussNewsId() {
        return discussNewsId;
    }

    public void setDiscussNewsId(Long discussNewsId) {
        this.discussNewsId = discussNewsId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public MsgDto create() {
        MsgDto msgDto=new MsgDto(new Date(),msgText);
        NewsDto newsDto=newsService.getById(discussNewsId);
        msgDto.setUserDto(userDto);
        msgDto.setNewsDto(newsDto);

        msgService.create(msgDto);
       return msgDto;
    }


    @Override
    public Map<MsgDto, UserDto> getMsgMap(){

        NewsDto newsDto=newsService.getById(discussNewsId);
        DiscussionDto discussionDto=newsDto.getDiscussionDto();
        Map<MsgDto, UserDto> mapMsgUsr=new LinkedHashMap();
        if (newsDto.getMsgSetDto().size()>0){
            Set<MsgDto> msgSetDto=newsDto.getMsgSetDto();
            List<MsgDto> msgListDto=new ArrayList<>();

            for(MsgDto m:msgSetDto){
                msgListDto.add(m);
            }
            Collections.sort(msgListDto,new SortByTimeMsg());



            for (MsgDto m:msgListDto){
                UserDto userDto=m.getUserDto();

                if(userDto!=null){
                    Long id=userDto.getId();
                    mapMsgUsr.put(m,userDto);
                    User user=mapper.map(userDto,User.class);
                    Discussion discussion=mapper.map(discussionDto,Discussion.class);
                    if(!isUserInDiscussion(user,discussion)){
                        user.getDiscussionSet().add(discussion);
                        discussion.getUserSet().add(user);
                        userService.updateUser(userDto);
                    }
                }
            }
//            System.out.println(mapMsgUsr);
        }
            return mapMsgUsr;
    }

    private boolean isUserInDiscussion(User user, Discussion discussion) {
        boolean isExist=false;
        UserDto userDto=mapper.map(user,UserDto.class);
        List<DiscussionDto> discussionsDto = discussionService.readByUser(userDto);
        if ((discussionsDto!=null)&&(discussionsDto.size()>0)){
            for (DiscussionDto d:discussionsDto){
                if((discussion!=null)&&(discussion.getId()==d.getId())){
                    isExist=true;
                }
            }
        }

        return isExist;
    }

}
