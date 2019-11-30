package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable pageable= PageRequest.of(page,maxResults, Sort.by("dateNews").descending());
        Map<News, User> map=new LinkedHashMap<>();
        List<News> list=newsService.findAllNews(pageable);
//        list=list.stream().sorted(new SortByTime()).collect(Collectors.toList());
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
    public Map<News, User> viewNewsOfUser(int page, int maxResults,User user) {  //page = number of page
        Map<News, User>map=new LinkedHashMap<>();
        Pageable pageable= PageRequest.of(page,maxResults);
        List<Long> list=newsService.findAllNewsByUserId(pageable,user.getId());
        list.stream()
                .map(l->newsService.getById(l))
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
