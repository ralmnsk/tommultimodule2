package com.github.ralmnsk.service.pagination;

public interface Paginator {
    void viewNews(int firstResult,int maxResults);
    void viewNewsOfUser(int firstResult,int maxResults);
}
