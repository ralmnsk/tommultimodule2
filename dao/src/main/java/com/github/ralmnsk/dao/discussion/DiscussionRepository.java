package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    @Query("select d from Discussion d where d.news.idNews = :newsId")
    List<Discussion> findByNewsId(@Param("newsId")Long newsId);

//    @Query("select d from Discussion d where d.userSet.User.id = :userId")
//    @Query("select d from Discussion d inner join d.userSet userSet where user.id = :userId")
    @Query("SELECT d FROM Discussion d INNER JOIN d.userSet u WHERE u.id = :userId")
    List<Discussion> findByUserId(@Param("userId")Long userId);

//    @Query("SELECT d FROM Discussion d INNER JOIN d.userSet u WHERE u.id = :userId ")
//    List<Discussion> findByUserIdAndNewsId(@Param("userId")Long userId,@Param("newsId")Long newsId);
}
