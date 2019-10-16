package com.github.ralmnsk.dao.msg;

import com.github.ralmnsk.model.msg.Msg;

import java.util.List;

public interface MsgDao {
    void create(Msg msg);
    Msg read(Long id);
    void update(Long id, String newText);
    void delete(Long id);
    List<Msg> findAll(int firstResult, int maxResults);

}