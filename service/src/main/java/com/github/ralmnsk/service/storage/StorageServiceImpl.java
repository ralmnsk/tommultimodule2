package com.github.ralmnsk.service.storage;

import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.model.storage.Storage;

import java.util.List;

public class StorageServiceImpl implements StorageService {
    private Storage storage;
    private StorageDao storageDao= StorageDaoImpl.getInstance();

    private static volatile StorageService instance;

    public static StorageService getInstance() {
        StorageService localInstance = instance;
        if (localInstance == null) {
            synchronized (StorageService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StorageServiceImpl();
                }
            }
        }
        return localInstance;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public List<Long> getNewsIdByUserId(Long userId) {
        return storageDao.getNewsIdByUserId(userId);
    }

    @Override
    public void createStorage(Long userId, Long newsId) {
        storageDao.createStorage(userId,newsId);
    }

    @Override
    public void deleteStorage(Long userId, Long newsId) {
        storageDao.deleteStorage(userId,newsId);
    }

    @Override
    public Long getUserIdByNewsId(Long newsId) {
        return storageDao.getUserIdByNewsId(newsId);
    }
}
