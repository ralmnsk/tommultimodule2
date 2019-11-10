package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("select n from News n where n.nameNews = :name")
    News findByName(@Param("name") String name);
}
