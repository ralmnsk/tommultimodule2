package com.github.ralmnsk.service.news;


import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
//@ComponentScan(basePackages = {"com.github.ralmnsk.model","com.github.ralmnsk.dao"})
public class NewsServiceRepoImpl implements NewsService{

//    @PersistenceContext
//    private EntityManager em;
    @Autowired
    private ModelMapper mapper;

    private NewsRepository newsRepo; //= UserDaoHiberImpl.getInstance();

    @Autowired
    public NewsServiceRepoImpl(NewsRepository newsRepo) {
        this.newsRepo=newsRepo;
    }

    @Override
    public void createNews(NewsDto newsDto) {
        News news=mapper.map(newsDto,News.class);
        newsRepo.save(news);
    }

    @Override
    public NewsDto readNews(NewsDto newsDto) {
        NewsDto readNewsDto=null;
        if ((newsDto.getIdNews()!=null)&&(newsDto.getIdNews()>0)){
            News news=mapper.map(newsDto,News.class);
            if(newsRepo.findById(news.getIdNews())!=null){
                News readNews=newsRepo.findById(news.getIdNews()).get();
                readNewsDto=mapper.map(readNews,NewsDto.class);
            }
        } else {
            News news=mapper.map(newsDto,News.class);
            News readNews= newsRepo.findByName(news.getNameNews());
            readNewsDto=mapper.map(readNews,NewsDto.class);
        }
        return readNewsDto;
    }

    @Override
    public void updateNews(NewsDto newsDto) {
        News news=mapper.map(newsDto,News.class);
        newsRepo.save(news);
    }

    @Override
    public void deleteNews(NewsDto newsDto) {
        News news=mapper.map(newsDto,News.class);
        newsRepo.delete(news);
    }

    @Override
    public List<Long> findAllNewsByUserId(Pageable pageable, Long userId) {
//        Page<News> pageNews=newsRepo.findAll(PageRequest.of(page,maxResults));
        int lim=pageable.getPageSize();
        int offs=(int)pageable.getOffset();
        List<Long> list=newsRepo.findAllNewsByUserId(userId);
        List<Long> newsList=new LinkedList<Long>();
        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                if ((i>=offs)&&(i<=(offs+lim-1))){
                    newsList.add(list.get(i));
                }
            }
        }
//        newsList.stream().forEach(System.out::println);
        return newsList;
    }

    @Override
    public List<NewsDto> findAllNews(Pageable pageable) {
        Page<News> pageNews=newsRepo.findAll(pageable);
        List<News> listDto=pageNews.getContent();
        List<NewsDto> list=listDto.stream()
                .map(d->mapper.map(d,NewsDto.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public NewsDto findByName(String name) {
        News news=newsRepo.findByName(name);
        NewsDto newsDto=mapper.map(news,NewsDto.class);
        return newsDto;
    }

    @Override
    public NewsDto getById(Long id) {
        Optional<News> byId = newsRepo.findById(id);
        News news=byId.get();
        if (news!=null){
            NewsDto newsDto=mapper.map(news,NewsDto.class);
            return newsDto;
        }
        return null;
    }

    @Override
    public Long countAllNews() {
        return newsRepo.countAllNews();
    }
}
