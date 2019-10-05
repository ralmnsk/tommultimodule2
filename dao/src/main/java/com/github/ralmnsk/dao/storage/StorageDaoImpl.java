package com.github.ralmnsk.dao.storage;

import com.github.ralmnsk.dao.connection.DataSource;
import com.github.ralmnsk.model.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDaoImpl implements StorageDao {
    private static Logger logger= LoggerFactory.getLogger(StorageDaoImpl.class);

    private static volatile StorageDao instance;

    public static StorageDao getInstance() {
        StorageDao localInstance = instance;
        if (localInstance == null) {
            synchronized (StorageDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StorageDaoImpl();
                }
            }
        }
        return localInstance;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public List<Long> getNewsIdByUserId(Long userId) {
        ResultSet rs=null;
        List<Long> list=new ArrayList<>();
        try(Connection connection=getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select idnews from user_storage where idusrtab=?", Statement.RETURN_GENERATED_KEYS);
                )

        {
            statement.setLong(1, userId);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
            while(rs.next()){
                list.add(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().getNewsIdByUserId():", ex);
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing StorageDaoImpl().getNewsIdByUserId().rs close:", e);
                }
            }
        }
        return list;
    }

    @Override
    public void createStorage(Long userId, Long newsId) {
        ResultSet generatedKeys=null;
        Storage storage=new Storage(userId,newsId);
        try (Connection connection=getConnection();
                PreparedStatement statement =
                        connection
                                .prepareStatement("insert into user_storage (idusrtab, idnews) values (?,?)"
                                        , Statement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setLong(1, userId);
            statement.setLong(2, newsId);
            statement.execute();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                storage.setId(generatedKeys.getLong(1));
                generatedKeys.close();
            logger.info("StorageDaoImpl: create storage: "+storage.getId()+" "+storage.getUsrId()+" "+storage.getNewsId());
            }
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().createStorage():", ex);
        }finally {
            if(generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteStorage(Long userId, Long newsId) {
        try (Connection connection = getConnection();
              PreparedStatement statement = connection.prepareStatement
                    ("delete from user_storage where idusrtab=? and idnews=?");

                ){
            statement.setLong(1, userId);
            statement.setLong(2, newsId);
            statement.execute();
            logger.info(this.getClass()+" deleteStorage()");
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().deleteStorage():", ex);
        }
    }

    @Override
    public Long getUserIdByNewsId(Long newsId) {
        ResultSet rs=null;
        Long userId=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                    ("select idusrtab from user_storage where idnews=?", Statement.RETURN_GENERATED_KEYS);
                ){
            statement.setLong(1, newsId);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
            while(rs.next()){
                userId=rs.getLong(1);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().getNewsIdByUserId():", ex);
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userId;
    }
}
