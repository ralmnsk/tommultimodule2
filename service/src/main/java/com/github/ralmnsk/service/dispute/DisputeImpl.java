package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class DisputeImpl implements Dispute {
    private HttpServletRequest req;

    public DisputeImpl(HttpServletRequest req) {
        this.req = req;
    }

    @Override
    public void get() {
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User readUser=userDao.getById(user.getId());

        if (readUser.getDiscussionSet().size()>0){
            Set<Discussion> discussionSet=readUser.getDiscussionSet();
            Map<Discussion, News> discussionNewsMap=new LinkedHashMap();
            for (Discussion d:discussionSet){
                discussionNewsMap.put(d,d.getNews());
            }
            session.setAttribute("discussionNewsMap",discussionNewsMap);
        } else{
            MsgDao msgDao= MsgDaoHiberImpl.getInstance();
            List<Msg> msgList=msgDao.findByUser(user);

        }
    }
}
