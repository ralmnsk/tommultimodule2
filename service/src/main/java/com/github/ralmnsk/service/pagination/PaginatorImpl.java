package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.dao.news.NewsDaoCounterForUserImpl;
import com.github.ralmnsk.dao.news.NewsDaoCounterImpl;
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
                int newsIndex=0;
                Collections.sort(newsList,new SortByTime());
                for (News n:newsList){
                    if((newsIndex>=firstResult)&&(newsIndex<=(firstResult+maxResults))){
                        map.put(n,readUser);
                    }
                        newsIndex++;
                }
            }
            session.setAttribute("map",map);
        }
    }


    public void pagination(int maxResults){
        HttpSession session=req.getSession();
        int currentPage=1;

        int step=0;
        int pagesCount=getCountOfPages(maxResults);

        if(session.getAttribute("currentPage")!=null){
            currentPage=(int)req.getSession().getAttribute("currentPage");
        }
        session.setAttribute("currentPage",currentPage);

        if (req.getParameter("move")!=null){
            String move=req.getParameter("move"); //next and previous on jsp
            switch (move){
                case "next": step=1;
                    currentPage=(currentPage<pagesCount)?(currentPage+step):currentPage;
                break;
                case "previous": step=-1;
                    currentPage=(currentPage>0)?(currentPage+step):currentPage;
                break;
            }
            if(currentPage==0){
                currentPage++;
            }
            session.setAttribute("currentPage",currentPage);
        }
        if(currentPage>0){
            viewNews((maxResults*(currentPage-1)),maxResults);
        } else {
            viewNews((maxResults*(currentPage)),maxResults);
        }
        req.setAttribute("pagesCount",pagesCount);
    }

    public void paginationForUserNews(int maxResults){
        HttpSession session=req.getSession();
        int currentPage=1;

        int step=0;
        User user=(User)req.getSession().getAttribute("user");
        UserService userService=UserServiceImpl.getInstance();
        User readUser=userService.readUser(user);
        int pagesCount=getCountOfPages(maxResults,user);

        if(session.getAttribute("currentPage")!=null){
            currentPage=(int)req.getSession().getAttribute("currentPage");
        }
        session.setAttribute("currentPage",currentPage);

        if (req.getParameter("move")!=null){
            String move=req.getParameter("move"); //next and previous on jsp
            switch (move){
                case "next": step=1;
                    currentPage=(currentPage<pagesCount)?(currentPage+step):currentPage;
                    break;
                case "previous": step=-1;
                    currentPage=(currentPage>0)?(currentPage+step):currentPage;
                    break;
            }
            if(currentPage==0){
                currentPage++;
            }
            session.setAttribute("currentPage",currentPage);
        }
        if(currentPage>0){
            viewNewsOfUser((maxResults*(currentPage-1)),maxResults);
        } else {
            viewNewsOfUser((maxResults*(currentPage)),maxResults);
        }
        req.setAttribute("pagesCount",pagesCount);
    }

    private int getCountOfPages(int maxResults) {
        int newsCount= NewsDaoCounterImpl
                .getInstance().getNewsCount();
        int countOfPages=newsCount/maxResults;
        int restOfNews=newsCount-countOfPages*maxResults;
        if(restOfNews>0){
            countOfPages++;
        }

        return countOfPages;
    }

    private int getCountOfPages(int maxResults,User user) {
        int newsCount= NewsDaoCounterForUserImpl
                .getInstance().getNewsCount(user);
        int countOfPages=newsCount/maxResults;
        int restOfNews=newsCount-countOfPages*maxResults;
        if(restOfNews>0){
            countOfPages++;
        }

        return countOfPages;
    }


}
