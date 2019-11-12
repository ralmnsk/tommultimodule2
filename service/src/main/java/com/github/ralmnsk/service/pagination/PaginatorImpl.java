package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PaginatorImpl implements Paginator{
    @Autowired
    private NewsService newsService;

    @Override
    public Map<News, User> viewNews(int page, int maxResults) {
        Map<News, User> map=new LinkedHashMap<>();
        List<News> list=newsService.findAllNews(page,maxResults);
        //sort list needed
        //Collections.sort(list,new SortByTime());
        list=list.stream().sorted(new SortByTime()).collect(Collectors.toList());
        list.stream().forEach(n->map.put(n,n.getUser()));
        return map;
    }

    @Override
    public Long getAllNewsCount(){
        Long count=0L;
            count=newsService.countAllNews();
        return count;
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

    @Override
    public int pagesCount(int allEntitiesCount, int maxEntitiesOnPage) {
        int count=1;
        if (allEntitiesCount==0){
            return 1;
        } else {
            count=allEntitiesCount/maxEntitiesOnPage;
            int restOfEntities=allEntitiesCount-maxEntitiesOnPage*count;
            return restOfEntities>0?count=count+1:count;
        }
    }
}
