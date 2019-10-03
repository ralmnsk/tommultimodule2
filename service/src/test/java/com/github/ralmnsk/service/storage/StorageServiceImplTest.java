package com.github.ralmnsk.service.storage;


import com.github.ralmnsk.model.storage.Storage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class StorageServiceImplTest {
    private Storage storage;
    private StorageService storageService;

    @BeforeEach
    public void setUp() throws Exception {
        storage=new Storage(1000L,1000L);
        storageService=new StorageServiceImpl();
    }

    @Test
    public void StorageServiceTest(){
        createStorage();
        getNewsIdByUserId();
        getUserIdByNewsId();
        deleteStorage();
    }

    public void createStorage() {
        storageService.createStorage(storage.getUsrId(),storage.getNewsId());
    }


    public void getNewsIdByUserId() {
        List<Long> list=storageService.getNewsIdByUserId(storage.getUsrId())
                .stream().collect(Collectors.toList());
        assertEquals(1000L,list.get(0));
    }


    public void deleteStorage() {
        storageService.deleteStorage(storage.getUsrId(),storage.getNewsId());
    }


    public void getUserIdByNewsId() {
        Long id=storageService.getUserIdByNewsId(storage.getNewsId());
        assertEquals(1000L,id);
    }
}