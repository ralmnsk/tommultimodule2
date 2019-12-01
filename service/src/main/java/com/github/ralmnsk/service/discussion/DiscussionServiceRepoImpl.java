package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dao.discussion.DiscussionRepository;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DiscussionServiceRepoImpl implements DiscussionService{
    private DiscussionRepository discussionRepository;

    @Autowired
    public void setDiscussionDao(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    @Override
    public boolean create(User user, News news) {
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
    public List<Discussion> readByUser(User user) {
        return discussionRepository.findByUserId(user.getId());//discussionRepository.(user);
    }

    @Override
    public void delete(Long id) {
        discussionRepository.deleteById(id);
    }

    @Override
    public List<Discussion> findAll(int page, int size) {
        return discussionRepository.findAll(PageRequest.of(page,size, Sort.by("id").descending())).getContent();
    }

    @Override
    public Long count() {
        return discussionRepository.count();
    }
}
