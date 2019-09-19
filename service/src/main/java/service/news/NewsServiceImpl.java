package service.news;

import dao.news.NewsDao;
import model.news.News;

public class NewsServiceImpl implements NewsService{

    private NewsDao newsDao;

    public void createNews(News news) {
        newsDao.createNews(news);
    }

    public News readNews(News news) {
        return newsDao.readNews(news);
    }

    public void updateNews(News news) {
        newsDao.updateNews(news);
    }

    public void deleteNews(News news) {
        newsDao.deleteNews(news);
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao=newsDao;
    }
}
