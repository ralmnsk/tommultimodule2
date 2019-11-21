package com.github.ralmnsk.service.news;


import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.model.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
//@ComponentScan(basePackages = {"com.github.ralmnsk.model","com.github.ralmnsk.dao"})
public class NewsServiceRepoImpl implements NewsService{

//    @PersistenceContext
//    private EntityManager em;

    private NewsRepository newsRepo; //= UserDaoHiberImpl.getInstance();

    @Autowired
    public NewsServiceRepoImpl(NewsRepository newsRepo) {
        this.newsRepo=newsRepo;
    }

    @Override
    public void createNews(News news) {
        newsRepo.save(news);
    }

    @Override
    public News readNews(News news) {
        News readNews=null;
        if ((news.getIdNews()!=null)&&(news.getIdNews()>0)){
            if(newsRepo.findById(news.getIdNews())!=null){
                readNews=newsRepo.findById(news.getIdNews()).get();
            }
        } else {
            readNews = newsRepo.findByName(news.getNameNews());
        }
        return readNews;
    }

    @Override
    public void updateNews(News news) {
        newsRepo.save(news);
    }

    @Override
    public void deleteNews(News news) {
        newsRepo.delete(news);
    }

    @Override
    public List<News> findAllNews(Pageable pageable) {
//        Page<News> pageNews=newsRepo.findAll(PageRequest.of(page,maxResults));
        Page<News> pageNews=newsRepo.findAll(pageable);
        return pageNews.getContent();
    }

    @Override
    public News findByName(String name) {
        return newsRepo.findByName(name);
    }

    @Override
    public News getById(Long id) {
        Optional<News> byId = newsRepo.findById(id);
        return byId.get();
    }

    @Override
    public Long countAllNews() {
        return newsRepo.countAllNews();
    }
}
