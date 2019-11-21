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
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NewsDeleterImpl implements NewsDeleter {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private DiscussionService discussionService;

    private News news;
    private User user;

    public NewsDeleterImpl(News news, User user) {
        this.news=news;
        this.user=user;
    }

    public NewsDeleterImpl() {
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void delete() {
        User readUser=userService.getById(user.getId());
        News readNews=newsService.getById(news.getIdNews());   //.getById(news.getIdNews());

        Discussion discussion=null;
        if(readNews.getDiscussion()!=null){
//            List<Discussion> discussionList=discussionDao.readByUser(readUser)
//                    .stream()
//                    .filter(d->d.getNews().getIdNews()==readNews.getIdNews())
//                    .collect(Collectors.toList());
            discussion=readNews.getDiscussion();
        }

        Set<News> newsSet=readUser.getNewsSet(); //.remove(0);
        if ((newsSet!=null)&&(newsSet.size()>0)){
            for(News n:newsSet){
                if (n.getIdNews().equals(readNews.getIdNews())){
                    if (discussion!=null){
                        discussionService.delete(discussion.getId());
                        log.info("discussion id={} of user={} newsName={}was deleted: {}",discussion.getId(),user.getName(),news.getNameNews());
                    }
                    newsService.deleteNews(readNews);
                    log.info("news id={} name={} was deleted: {}",readNews.getIdNews(),readNews.getNameNews());
                }
            }
        }
    }
}
