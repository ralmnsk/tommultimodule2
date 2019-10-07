package com.github.ralmnsk.service.storage;


import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class StorageServiceImplTest {
    @Mock
    private StorageDaoImpl storageDao;

    @InjectMocks
    private StorageServiceImpl storageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createStorage() {
        storageService.createStorage(2001L,2002L);
        verify(storageDao,times(1)).createStorage(2001L,2002L);

    }

    @Test
    public void getNewsIdByUserId() {
        storageService.createStorage(2001L,2002L);
        List<Long> listNewsId=new ArrayList<Long>();
        listNewsId.add(2002L);
        when(storageService.getNewsIdByUserId(2001L)).thenReturn(listNewsId);
        List<Long> testList=storageService.getNewsIdByUserId(2001L);
        verify(storageDao,times(1)).getNewsIdByUserId(2001L);
        assertEquals(listNewsId.get(0),testList.get(0));
    }


    public void deleteStorage() {
        storageService.createStorage(2001L,2002L);
        storageService.deleteStorage(2001L,2002L);
        verify(storageDao,times(1)).deleteStorage(2001L,2002L);
    }


    public void getUserIdByNewsId() {
        storageService.createStorage(2001L,2002L);
        Long userId=2001L;
        when(storageService.getUserIdByNewsId(2002L)).thenReturn(userId);
        Long testUserId=storageService.getUserIdByNewsId(2002L);
        verify(storageDao,times(1)).getUserIdByNewsId(2001L);
        assertEquals(testUserId,userId);
    }
}