package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

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
//        User readUser=userService.getById(user.getId());
//        News readNews=newsService.getById(news.getIdNews());   //.getById(news.getIdNews());

        Discussion discussion=null;
        if(news.getDiscussion()!=null){
//            List<Discussion> discussionList=discussionDao.readByUser(readUser)
//                    .stream()
//                    .filter(d->d.getNews().getIdNews()==readNews.getIdNews())
//                    .collect(Collectors.toList());
            discussion=news.getDiscussion();
        }

        Set<News> newsSet=user.getNewsSet(); //.remove(0);
        if ((newsSet!=null)&&(newsSet.size()>0)){
            for(News n:newsSet){
                if (n.getIdNews().equals(news.getIdNews())){
                    if (discussion!=null){
                        log.info("discussion id={} of user={} newsName={} was deleted: {}",discussion.getId(),user.getName(),news.getNameNews());
                        user.getDiscussionSet().remove(discussion);
                        userService.updateUser(user);
                        discussion.getUserSet().remove(user);
                        news.setDiscussion(null);
                        newsService.updateNews(news);
                        discussion.setNews(null);
                        discussionService.delete(discussion.getId());
                    }
                    log.info("news id={} name={} was deleted: {}",news.getIdNews(),news.getNameNews());
                    news.setUser(null);
                    user.getNewsSet().remove(news);
                    newsService.deleteNews(news);
                    break;
                }
            }
        }
    }
}
