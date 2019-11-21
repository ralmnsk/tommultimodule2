package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.model.msg.Msg;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MsgService {
    void create(Msg msg);
    Msg read(Long id);
    void update(Long id, String newText);
    void delete(Long id);
    List<Msg> findAll(Pageable pageable);

}
