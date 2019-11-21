package com.github.ralmnsk.dao.msg;

import com.github.ralmnsk.model.msg.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepository extends JpaRepository<Msg,Long> {

}
