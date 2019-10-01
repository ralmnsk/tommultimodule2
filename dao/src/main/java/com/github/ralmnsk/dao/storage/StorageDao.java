package com.github.ralmnsk.dao.storage;

import com.github.ralmnsk.model.storage.Storage;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface StorageDao {
    List<Long> getNewsIdByUserId(Long userId);
    void createStorage(Long userId,Long newsId);
    //Storage readStorage(Long userId,Long newsId);
    //void updateStorage(Long userId,Long newsId);
    void deleteStorage(Long userId,Long newsId);
    Long getUserIdByNewsId(Long newsId);
}
