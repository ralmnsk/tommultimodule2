package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.dao.connection.DataSource;
import com.github.ralmnsk.dao.connection.HibernateUtil;
import com.github.ralmnsk.model.user.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;


public class UserDaoHiberImpl implements UserDao{
    private static Logger logger= LoggerFactory.getLogger(UserDaoHiberImpl.class);
    private static volatile UserDao instance;

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDaoHiberImpl();
                }
            }
        }
        return localInstance;
    }

    private Connection getConnection() throws SQLException{
    return DataSource.getInstance().getConnection();
    }


    public void createUser(User user) {  //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }


    public User readUser(User user) { //retrofitted

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User readUser=null;
        Query<User> query=session.createQuery("from User where usr_name = :paramName"); //User is @Entity where @Column (name="usr_name")
        query.setParameter("paramName", user.getName());
        List<User> list=query.list();

        if(list.size()>0){
            readUser=list.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return readUser;
    }

    public void updateUser(User user) { //retrofitted
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
            if(readUser(user)!=null){
                session.update(user);
            }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query<User> query=session.createQuery("delete from User where usr_name = :paramName");
        query.setParameter("paramName", user.getName());
//        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getById(Long id) {
        User user=new User();
        ResultSet rs=null;
        try(
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select * from usrtab where id=?",Statement.RETURN_GENERATED_KEYS);
                )
        {

            statement.setLong(1, id);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
            while(rs.next()){
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setPass(rs.getString(3));
                user.setJoinDate(rs.getTimestamp(4));
                user.setRole(rs.getString(5));
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UserServiceImpl().getById():", ex);
        } finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing UserServiceImpl().getById().close rs:", e);
                }
            }
        }
        return user;
    }
}
