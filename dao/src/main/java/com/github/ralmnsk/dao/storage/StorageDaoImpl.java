package com.github.ralmnsk.dao.storage;

import com.github.ralmnsk.dao.connection.SingletonConnection;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.storage.Storage;
import com.github.ralmnsk.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDaoImpl implements StorageDao {
    private static Logger logger= LoggerFactory.getLogger(StorageDaoImpl.class);

    private Connection getConnection() throws SQLException {
        return SingletonConnection.getInstance().getConnection();
    }

    @Override
    public List<Long> getNewsIdByUserId(Long userId) {
        Connection connection= null;
        List<Long> list=new ArrayList<>();
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select idnews from user_storage where idusrtab=?", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userId);
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                list.add(rs.getLong(1));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().getNewsIdByUserId():", ex);
        }
        return list;
    }

    @Override
    public void createStorage(Long userId, Long newsId) {
        Storage storage=new Storage(userId,newsId);
        Connection connection= null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection
                            .prepareStatement("insert into user_storage (idusrtab, idnews) values (?,?)"
                                    , Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userId);
            statement.setLong(2, newsId);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                storage.setId(generatedKeys.getLong(1));
                //logger.info
                  logger.info("StorageDaoImpl: create storage: "+storage.getId()+" "+storage.getUsrId()+" "+storage.getNewsId());
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().createStorage():", ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in createUser(): connection.close():"+e);
//            }
//        }
    }

    @Override
    public void deleteStorage(Long userId, Long newsId) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("delete from user_storage where idusrtab=? and idnews=?");
            statement.setLong(1, userId);
            statement.setLong(2, newsId);
            statement.execute();
            statement.close();
            logger.info(this.getClass()+" deleteStorage()");
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().deleteStorage():", ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.info("Exception in deleteUser(): connection.close():"+e);
//            }
//        }
    }

    @Override
    public Long getUserIdByNewsId(Long newsId) {
        Connection connection= null;
        Long userId=null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select idusrtab from user_storage where idnews=?", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, newsId);
            statement.execute();
            ResultSet rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                userId=rs.getLong(1);
            }
            rs.close();
            statement.close();
            logger.info(this.getClass()+" getUserIdByNewsId()");
        } catch (SQLException ex) {
            logger.error("Problem executing StorageDaoImpl().getNewsIdByUserId():", ex);
        }
        return userId;
    }
}
