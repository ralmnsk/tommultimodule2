package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.msg.MsgRepository;
import com.github.ralmnsk.dto.MsgDto;
import com.github.ralmnsk.model.msg.Msg;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MsgRepository msgRepo;

    @Override
    public void create(MsgDto msgDto) {
        Msg msg=mapper.map(msgDto,Msg.class);
        msgRepo.save(msg);
    }

    @Override
    public MsgDto read(Long id) {
        Msg msg=msgRepo.getOne(id);
        if (msg!=null){
            MsgDto msgDto=mapper.map(msg,MsgDto.class);
            return msgDto;
        }
        return null;
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
    public List<MsgDto> findAll(Pageable pageable) {
        List<Msg> list=msgRepo.findAll(pageable).getContent();
        if ((list!=null)&&(list.size()>0)){
            List<MsgDto> listDto=list.stream()
                    .map(m->mapper.map(m,MsgDto.class))
                    .collect(Collectors.toList());
            return listDto;
        }
        return null;
    }
}
