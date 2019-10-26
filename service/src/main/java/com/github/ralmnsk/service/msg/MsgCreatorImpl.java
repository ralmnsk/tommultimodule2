package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.news.comparator.SortByTimeMsg;
import com.github.ralmnsk.service.news.editor.NewsEditorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class MsgCreatorImpl implements MsgCreator{
    private static Logger logger= LoggerFactory.getLogger(MsgCreatorImpl.class);

    private HttpServletRequest req;
    private Long discussNewsId;

    public MsgCreatorImpl(HttpServletRequest req, Long discussNewsId) {
        this.req = req;
        this.discussNewsId = discussNewsId;
    }

    @Override
    public Msg create() {
        HttpSession session=req.getSession();
        String msgText=req.getParameter("msgText");
        Msg msg=new Msg(new Date(),msgText);
        News news=(News)session.getAttribute("news");
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        News readNews=newsDao.getById(news.getIdNews());

        User user=(User)session.getAttribute("user");
//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        User readUser=userDao.getById(user.getId());

        MsgDao msgDao= MsgDaoHiberImpl.getInstance();

        msg.setUser(user);
        msg.setNews(news);

        msgDao.create(msg);
        //getMsgList();
        return msg;
    }

    @Override
    public void getMsgList(){
        HttpSession session=req.getSession();
        News newsUnconfirmed=(News)session.getAttribute("news");
        News news=NewsDaoHiberImpl.getInstance().getById(newsUnconfirmed.getIdNews());
        if (news.getMsgSet().size()>0){
            Set<Msg> msgSet=news.getMsgSet();
            List<Msg> msgList=new ArrayList<>();

            for(Msg m:msgSet){
                msgList.add(m);
            }
            Collections.sort(msgList,new SortByTimeMsg());

            Map<Msg, User> mapMsgUsr=new LinkedHashMap();

            for (Msg m:msgList){
                User user=m.getUser();

                if(user!=null){
                    Long id=user.getId();
//                    User readUser=userService.getById(id);
                    mapMsgUsr.put(m,user);
                }
            }
            session.setAttribute("mapMsgUsr",mapMsgUsr);
            System.out.println(mapMsgUsr);
        }
    }
}
