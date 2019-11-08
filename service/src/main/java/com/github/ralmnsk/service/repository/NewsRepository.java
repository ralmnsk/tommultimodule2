package com.github.ralmnsk.service.repository;

import com.github.ralmnsk.model.news.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
