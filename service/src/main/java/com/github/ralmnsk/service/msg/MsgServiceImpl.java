package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.model.msg.Msg;

import java.util.List;

public class MsgServiceImpl implements MsgService {

    private MsgDao msgDao= MsgDaoHiberImpl.getInstance();

    private static volatile MsgService instance;

    public static MsgService getInstance() {
        MsgService localInstance = instance;
        if (localInstance == null) {
            synchronized (MsgService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MsgServiceImpl();
                }
            }
        }
        return localInstance;
    }
    @Override
    public void create(Msg msg) {
        msgDao.create(msg);
    }

    @Override
    public Msg read(Long id) {
        return msgDao.read(id);
    }

    @Override
    public void update(Long id, String newText) {
        msgDao.update(id,newText);
    }

    @Override
    public void delete(Long id) {
        msgDao.delete(id);
    }

    @Override
    public List<Msg> findAll(int firstResult, int maxResults) {
        return msgDao.findAll(firstResult,maxResults);
    }
}
