package com.github.ralmnsk.service.repository;

import com.github.ralmnsk.model.msg.Msg;
import org.springframework.data.repository.CrudRepository;

public interface MsgRepository extends CrudRepository<Msg, Long> {
}
