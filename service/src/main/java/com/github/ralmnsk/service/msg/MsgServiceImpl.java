package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.msg.MsgRepository;
import com.github.ralmnsk.model.msg.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgRepository msgRepo;

    @Override
    public void create(Msg msg) {
        msgRepo.save(msg);
    }

    @Override
    public Msg read(Long id) {
        return msgRepo.getOne(id);
    }

    @Override
    public void update(Long id, String newText) {
        Msg msg=msgRepo.getOne(id);
        msg.setText(newText);
        msgRepo.save(msg);
    }

    @Override
    public void delete(Long id) {
        msgRepo.deleteById(id);
    }

    @Override
    public List<Msg> findAll(Pageable pageable) {
        return msgRepo.findAll(pageable).getContent();
    }
}
