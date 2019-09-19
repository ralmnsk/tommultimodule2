package my.tomcat.app.news.creator;

import dao.news.NewsDao;
import dao.news.NewsDaoImpl;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.news.News;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.news.NewsService;
import service.news.NewsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class NewsCreator {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private News news;

    private static Logger logger= LoggerFactory.getLogger(NewsCreator.class);

    public NewsCreator(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public News newsCreate(){
        User user=(User)req.getSession().getAttribute("user");
        String dataNews=req.getParameter("dataNews");
        News news=new News();
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);

        if (!dataNews.isEmpty()){
            news=new News(user.getId(), req.getParameter("nameNews"), dataNews, new Date(new java.util.Date().getTime()));
            logger.info("news created:"+news.toString());
            newsService.createNews(news);
        } else {
            logger.info("news created:"+news.toString()+" is empty");
        }

    return news;
    }
}
