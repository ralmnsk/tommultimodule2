package dao.connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class SingletonConnectionTest {
    //@InjectMocks
    private SingletonConnection dbConnection;

    {
        try {
            dbConnection = SingletonConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Mock
    private Connection mockConnection;

    @Mock private Statement mockStatement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getConnectionTest() throws SQLException {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate((String)Mockito.any())).thenReturn(1);
        int value = dbConnection.getConnection().createStatement().executeQuery("select * from USRTAB").findColumn("ID");     //.prepareStatement("select * from USRTAB where name=?").executeUpdate();  //executeQuery("");
        Assert.assertEquals(value, 1);
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
    }
}