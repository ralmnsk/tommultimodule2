package com.github.ralmnsk.dao.storage;

import com.github.ralmnsk.model.storage.Storage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageDaoImplTest {
    private StorageDao storageDao=StorageDaoImpl.getInstance();
    @Test
    void getInstance() {
        assertNotNull(storageDao);
    }

    @Test
    void getNewsIdByUserId() {
        storageDao.createStorage(2001L,2002L);
        List<Long> newsList=storageDao.getNewsIdByUserId(2001L);
        Long newsId= newsList.get(0);
        assertEquals(2002L,newsId);
        storageDao.deleteStorage(2001L,2002L);
    }

    @Test
    void createStorage() {
        storageDao.createStorage(2001L,2002L);
        List<Long> newsList=storageDao.getNewsIdByUserId(2001L);
        Long newsId= newsList.get(0);
        assertNotNull(newsId);
        storageDao.deleteStorage(2001L,2002L);
    }

    @Test
    void deleteStorage() {
        storageDao.createStorage(2001L,2002L);
        storageDao.deleteStorage(2001L,2002L);
        List<Long> newsList=storageDao.getNewsIdByUserId(2001L);
        assertTrue(newsList.size()==0);
    }

    @Test
    void getUserIdByNewsId() {
        storageDao.createStorage(2001L,2002L);
        Long userId = storageDao.getUserIdByNewsId(2002L);
        assertEquals(2001L,userId);
        storageDao.deleteStorage(2001L,2002L);
    }
}