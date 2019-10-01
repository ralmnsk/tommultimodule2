package com.github.ralmnsk.service.storage;

import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface StorageService {
    List<Long> getNewsIdByUserId(Long userId);
    User getUserIdByNewsId(Long newsId);
    void createStorage(Long userId,Long newsId);
    void deleteStorage(Long userId,Long newsId);
    void setStorageDao(StorageDao storageDao);
}
