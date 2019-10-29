package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewsDeleterImpl implements NewsDeleter {
    private News news;
    private User user;

    public NewsDeleterImpl(News news, User user) {
        this.news=news;
        this.user=user;
    }

    @Override
    public void delete() {

        UserDao userDao= UserDaoHiberImpl.getInstance();
        User readUser=userDao.readUser(user);
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        News readNews=newsDao.readNews(news);   //.getById(news.getIdNews());
        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();

        Discussion discussion=null;
        if(news.getDiscussion()!=null){
            List<Discussion> discussionList=discussionDao.readByUser(readUser)
                    .stream()
                    .filter(d->d.getNews().getIdNews()==readNews.getIdNews())
                    .collect(Collectors.toList());
        }

        Set<News> newsSet=readUser.getNewsSet(); //.remove(0);
        if ((newsSet!=null)&&(newsSet.size()>0)){
            for(News n:newsSet){
                if (n.getIdNews()==readNews.getIdNews()){
                    if (discussion!=null){
                        discussionDao.delete(discussion.getId());
                    }
                    newsDao.deleteNews(readNews);
                }
            }

        }
    }
}
