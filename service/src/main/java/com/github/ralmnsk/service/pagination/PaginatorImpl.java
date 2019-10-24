package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class PaginatorImpl implements Paginator {

    private HttpServletRequest req;
    private HttpServletResponse resp;

    public PaginatorImpl(HttpServletRequest req, HttpServletResponse resp){
        this.req=req;
        this.resp=resp;
    }

    public void viewNews(int firstResult,int maxResults) {

        NewsService newsService= NewsServiceImpl.getInstance();
        UserService userService= UserServiceImpl.getInstance();

        List<News> newsList=newsService.findAllNews(firstResult,maxResults);
        Collections.sort(newsList,new SortByTime());
        Map<News, User> map=new LinkedHashMap();
        if (newsList.size()>0){

            for (News news:newsList){
                User user=news.getUser();
                if(user!=null){
                    Long id=user.getId();
                    User readUser=userService.getById(id);
                    map.put(news,readUser);

                }
            }
            HttpSession session=req.getSession();
            session.setAttribute("map",map);
        }
    }

    public void viewNewsOfUser(int firstResult,int maxResults) {

        UserService userService=UserServiceImpl.getInstance();
        User user=(User)req.getSession().getAttribute("user");
        User readUser=userService.readUser(user);

        if (readUser!=null){
            Set<News> newsSet=readUser.getNewsSet();
            Map<News,User> map=new LinkedHashMap();
            HttpSession session=req.getSession();
            if ((newsSet!=null)&&(newsSet.size()>0)){
                List<News> newsList=new ArrayList<News>();
                for (News news:newsSet){
                    newsList.add(news);
                }
                Collections.sort(newsList,new SortByTime());
                for (News n:newsList){
                    map.put(n,readUser);
                }
            }
            session.setAttribute("map",map);
        }
    }
}
