package service.news;

import dao.news.NewsDao;
import model.news.News;

import java.util.List;

public interface NewsService {
    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    void setNewsDao(NewsDao newsDao);
    List<News> findAllNews();
}
