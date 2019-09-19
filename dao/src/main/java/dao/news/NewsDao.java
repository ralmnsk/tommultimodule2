package dao.news;


import model.news.News;

public interface NewsDao {
    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
}
