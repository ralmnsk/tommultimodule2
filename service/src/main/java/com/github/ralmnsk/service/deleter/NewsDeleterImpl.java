package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Slf4j
@Service
public class NewsDeleterImpl implements NewsDeleter {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private ModelMapper mapper;

    private NewsDto newsDto;
    private UserDto userDto;

    public NewsDeleterImpl(NewsDto newsDto, UserDto userDto) {
        this.newsDto=newsDto;
        this.userDto=userDto;
    }

    public NewsDeleterImpl() {
    }

    public NewsDto getNewsDto() {
        return newsDto;
    }

    public void setNewsDto(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public void delete() {
//        User readUser=userService.getById(user.getId());
//        News readNews=newsService.getById(news.getIdNews());   //.getById(news.getIdNews());

        DiscussionDto discussionDto=null;
        if(newsDto.getDiscussionDto()!=null){
//            List<Discussion> discussionList=discussionDao.readByUser(readUser)
//                    .stream()
//                    .filter(d->d.getNews().getIdNews()==readNews.getIdNews())
//                    .collect(Collectors.toList());
            discussionDto=newsDto.getDiscussionDto();
        }

        Set<NewsDto> newsSetDto=userDto.getNewsSetDto(); //.remove(0);
        if ((newsSetDto!=null)&&(newsSetDto.size()>0)){
            for(NewsDto n:newsSetDto){
                if (n.getIdNews().equals(newsDto.getIdNews())){
                    if (discussionDto!=null){
                        log.info("discussion id={} of user={} newsName={} was deleted: {}",discussionDto.getId(),userDto.getName(),newsDto.getNameNews());
                        userDto.getDiscussionSetDto().remove(discussionDto);
                        userService.updateUser(userDto);
                        discussionDto.getUserSetDto().remove(userDto);
                        newsDto.setDiscussionDto(null);
                        newsService.updateNews(newsDto);
                        discussionDto.setNewsDto(null);
                        discussionService.delete(discussionDto.getId());
                    }
                    log.info("news id={} name={} was deleted: {}",newsDto.getIdNews(),newsDto.getNameNews());
                    newsDto.setUserDto(null);
                    userDto.getNewsSetDto().remove(newsDto);
                    newsService.deleteNews(newsDto);
                    break;
                }
            }
        }
    }
}
