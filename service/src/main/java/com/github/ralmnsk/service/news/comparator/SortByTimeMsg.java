package com.github.ralmnsk.service.news.comparator;

import com.github.ralmnsk.dto.MsgDto;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;

import java.util.Comparator;

public class SortByTimeMsg implements Comparator<MsgDto> {
    @Override
    public int compare(MsgDto n1, MsgDto n2) {
        if(n1.getDate().getTime()<n2.getDate().getTime()){
            return -1;
        } else if(n1.getDate().getTime()>n2.getDate().getTime()){
            return 1;
        } else {

            return 0;
        }
    }
}
