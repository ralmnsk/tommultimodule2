package com.github.ralmnsk.service.news.comparator;

import com.github.ralmnsk.model.news.News;

import java.util.Comparator;

public class SortByTime implements Comparator<News> {
    @Override
    public int compare(News n1, News n2) {
         if(n1.getDateNews().getTime()>n2.getDateNews().getTime()){
             return -1;
         } else if(n1.getDateNews().getTime()<n2.getDateNews().getTime()){
             return 1;
         } else {

                return 0;
         }
    }
}
