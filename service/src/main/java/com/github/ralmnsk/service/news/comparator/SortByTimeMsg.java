package com.github.ralmnsk.service.news.comparator;

import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;

import java.util.Comparator;

public class SortByTimeMsg implements Comparator<Msg> {
    @Override
    public int compare(Msg n1, Msg n2) {
        if(n1.getDate().getTime()>n2.getDate().getTime()){
            return -1;
        } else if(n1.getDate().getTime()<n2.getDate().getTime()){
            return 1;
        } else {

            return 0;
        }
    }
}
