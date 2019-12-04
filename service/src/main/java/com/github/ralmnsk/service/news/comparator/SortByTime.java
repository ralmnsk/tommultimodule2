package com.github.ralmnsk.service.news.comparator;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;

import java.util.Comparator;

public class SortByTime implements Comparator<NewsDto> {
    @Override
    public int compare(NewsDto n1, NewsDto n2) {
         if(n1.getDateNews().getTime()>n2.getDateNews().getTime()){
             return -1;
         } else if(n1.getDateNews().getTime()<n2.getDateNews().getTime()){
             return 1;
         } else {

                return 0;
         }
    }

}
