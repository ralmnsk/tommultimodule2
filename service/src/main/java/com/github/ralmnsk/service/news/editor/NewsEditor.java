package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;

public interface NewsEditor {
    NewsDto newsEdit();
    void setId(Long id);
}
