package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.model.news.News;

public interface NewsEditor {
    News newsEdit();
    void setId(Long id);
}
