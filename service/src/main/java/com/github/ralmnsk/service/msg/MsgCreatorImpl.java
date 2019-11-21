package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
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

    private Long discussNewsId;
    private String msgText;
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Msg create() {
        Msg msg=new Msg(new Date(),msgText);
        News news=newsService.getById(discussNewsId);
        msg.setUser(user);
        msg.setNews(news);

        msgService.create(msg);
       return msg;
    }


    @Override
    public Map<Msg, User> getMsgMap(){

        News news=newsService.getById(discussNewsId);
        Discussion discussion=news.getDiscussion();
        Map<Msg, User> mapMsgUsr=new LinkedHashMap();
        if (news.getMsgSet().size()>0){
            Set<Msg> msgSet=news.getMsgSet();
            List<Msg> msgList=new ArrayList<>();

            for(Msg m:msgSet){
                msgList.add(m);
            }
            Collections.sort(msgList,new SortByTimeMsg());



            for (Msg m:msgList){
                User user=m.getUser();

                if(user!=null){
                    Long id=user.getId();
                    mapMsgUsr.put(m,user);
                    if(!isUserInDiscussion(user,discussion)){
                        user.getDiscussionSet().add(discussion);
                        discussion.getUserSet().add(user);
                        userService.updateUser(user);
                    }
                }
            }
//            System.out.println(mapMsgUsr);
        }
            return mapMsgUsr;
    }

    private boolean isUserInDiscussion(User user, Discussion discussion) {
        boolean isExist=false;

        List<Discussion> discussions = discussionService.readByUser(user);
        if ((discussions!=null)&&(discussions.size()>0)){
            for (Discussion d:discussions){
                if((discussion!=null)&&(discussion.getId()==d.getId())){
                    isExist=true;
                }
            }
        }

        return isExist;
    }

}
