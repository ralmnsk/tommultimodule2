package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.storage.StorageService;
import com.github.ralmnsk.service.storage.StorageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;

public class NewsCreatorImpl implements NewsCreator {
    private User user;
    private String dataNews;
    private String nameNews;

    private static Logger logger= LoggerFactory.getLogger(NewsCreatorImpl.class);

    public NewsCreatorImpl(User user, String dataNews, String nameNews) {
        this.user = user;
        this.dataNews = dataNews;
        this.nameNews=nameNews;
    }

    @Override
    public News newsCreate(){
        News news=new News();
        //NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=NewsServiceImpl.getInstance();
        //newsService.setNewsDao(newsDao);

        if (!dataNews.isEmpty()){
            news=new News(user.getId(), nameNews, dataNews, new Timestamp(new java.util.Date().getTime()));
            logger.info("news created:"+news.toString());
            newsService.createNews(news);
            News newsFromBase=newsService.readNews(news);
            //StorageDao storageDao=new StorageDaoImpl();
            StorageService storageService=StorageServiceImpl.getInstance();
            //storageService.setStorageDao(storageDao);
            storageService.createStorage(user.getId(),newsFromBase.getIdNews());
            logger.info(this.getClass()+" newsCreate()");
        } else {
            logger.info("news created: {} is empty", news);
        }

    return news;
    }


}
