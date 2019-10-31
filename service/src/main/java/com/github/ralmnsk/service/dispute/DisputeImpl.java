package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
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
        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
        List<Discussion> discussionList=discussionDao.readByUser(readUser);
        if ((discussionList!=null)&&(discussionList.size()>0)){
            session.setAttribute("discussionList",discussionList);
        }
//        else{
//            MsgDao msgDao= MsgDaoHiberImpl.getInstance();
//            List<Msg> msgList=msgDao.findByUser(user);
//        }
    }
}
