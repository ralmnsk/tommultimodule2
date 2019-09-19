package service.news;

import dao.news.NewsDao;
import model.news.News;

public interface NewsService {
    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    void setNewsDao(NewsDao newsDao);
}
