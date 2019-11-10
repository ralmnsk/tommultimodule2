package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PaginatorImpl implements Paginator{
    @Autowired
    private NewsService newsService;

    @Override
    public Map<News, User> viewNews(int page, int maxResults) {
        Map<News, User>map=new HashMap<>();
        List<News> list=newsService.findAllNews(page,maxResults);
        //sort list needed
        list.stream().forEach(n->map.put(n,n.getUser()));
        return map;
    }

    @Override
    public Map<News, User> viewNewsOfUser(int page, int maxResults,User user) {
        Map<News, User>map=new HashMap<>();
        List<News> list=newsService.findAllNews(page,maxResults);
        list.stream()
                .filter(n->n.getUser().getName().equals(user.getName()))
                .forEach(n->map.put(n,n.getUser()));
        return map;
    }
}
