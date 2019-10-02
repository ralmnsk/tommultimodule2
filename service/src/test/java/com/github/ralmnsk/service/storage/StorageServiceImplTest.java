package com.github.ralmnsk.service.storage;

import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.model.storage.Storage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StorageServiceImplTest {
    private Storage storage;
    private StorageDao storageDao;
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
        storage=new Storage(29L,19L);
        storageDao=new StorageDaoImpl();
        storageService=new StorageServiceImpl();
        storageService.setStorageDao(storageDao);
    }

    @Test
    public void createStorage() {
        storageService.createStorage(storage.getUsrId(),storage.getNewsId());
    }

    @Test
    public void getNewsIdByUserId() {
        storageService.getNewsIdByUserId(storage.getUsrId())
                .stream().map(id->"user id:"+storage.getUsrId()+" news id:"+id)
                .forEach(System.out::println);
    }

    @Test
    public void deleteStorage() {
    }

    @Test
    public void getUserIdByNewsId() {
        System.out.println("user id: "+storageService.getUserIdByNewsId(19L));
    }
}