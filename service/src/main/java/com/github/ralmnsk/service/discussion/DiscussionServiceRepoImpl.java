package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dao.discussion.DiscussionRepository;
import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceRepoImpl implements DiscussionService{
    @Autowired
    private ModelMapper mapper;

    private DiscussionRepository discussionRepository;

    @Autowired
    public void setDiscussionDao(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    @Override
    public boolean create(UserDto userDto, NewsDto newsDto) {
        News news=mapper.map(newsDto,News.class);
        User user=mapper.map(userDto,User.class);
        if(!isExist(user,news)){
            Discussion discussion=new Discussion();
            discussion.setNews(news);
            discussion.getUserSet().add(user);
            user.getDiscussionSet().add(discussion);
            discussionRepository.save(discussion);
            return true;
        }
        return false;
    }

    private boolean isExist(User user, News news) {
        List<Discussion> listByNews=discussionRepository.findByNewsId(news.getIdNews());
        List<Discussion> listByUser=discussionRepository.findByUserId(user.getId());
        List<Discussion> endList=new LinkedList<>();
        for (Discussion d:listByNews){
            listByUser.stream().filter(disc->disc.getId()==d.getId()).forEach(disc->endList.add(disc));
        }

        if (endList.size()>0){
            return true;
        }

        return false;
    }


    @Override
    public List<DiscussionDto> readByUser(UserDto userDto) {
        User user=mapper.map(userDto,User.class);
        List<Discussion> list=discussionRepository.findByUserId(user.getId());
        if ((list!=null)&&list.size()>0){
            List<DiscussionDto> listDto=list
                    .stream()
                    .map(d->mapper.map(d,DiscussionDto.class))
                    .collect(Collectors.toList());
            return listDto;
        }
        return null;//discussionRepository.(user);
    }

    @Override
    public void delete(Long id) {
        discussionRepository.deleteById(id);
    }

    @Override
    public List<DiscussionDto> findAll(int page, int size) {
        List<Discussion> list=discussionRepository.findAll(PageRequest.of(page,size, Sort.by("id").descending())).getContent();
        if ((list!=null)&&list.size()>0){
            List<DiscussionDto> listDto=list
                    .stream()
                    .map(d->mapper.map(d,DiscussionDto.class))
                    .collect(Collectors.toList());
            return listDto;
        }
        return null;
    }

    @Override
    public Long count() {
        return discussionRepository.count();
    }
}
