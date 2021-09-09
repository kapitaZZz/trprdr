package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Complete!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to create table " + e);
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Table unable to drop " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            user.setId(null);
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to save new user " + e);
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM users WHERE id = :id";

            Query query = session.createSQLQuery(sql).setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Unable to remove current user " + e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            return session.getSessionFactory().getCurrentSession().createCriteria(User.class).list();
        } catch (Exception e) {
            System.out.println("Unable to get any data from table " + e);
        }
        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM users";

            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Nothing to delete in table " + e);
        } finally {
            session.close();
        }
    }
}
