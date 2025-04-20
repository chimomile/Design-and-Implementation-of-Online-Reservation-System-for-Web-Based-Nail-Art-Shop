package DAO;

import pojo.NailartUtil;
import pojo.Login;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    // Retrieve all login details
    public List<Login> retrieveLogin() {
        List<Login> loginList = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Login");
            loginList = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return loginList;
    }


    // Get login details by ID
    public List<Login> getById(String id) {
        List<Login> loginList = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Login where id = :id");
            query.setString("id", id);
            loginList = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return loginList;
    }

    // Search for login by ID and email
    public List<Login> searchLogin(String id, String email) {
        List<Login> loginList = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();

            // Build query based on non-null parameters
            String queryString = "from Login where 1=1";
            if (id != null && !id.isEmpty()) {
                queryString += " and id = :loginId";
            }
            if (email != null && !email.isEmpty()) {
                queryString += " and email like :loginEmail";
            }

            Query query = session.createQuery(queryString);

            // Set parameters dynamically
            if (id != null && !id.isEmpty()) {
                query.setString("loginId", id);
            }
            if (email != null && !email.isEmpty()) {
                query.setString("loginEmail", "%" + email + "%");
            }

            loginList = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return loginList;
    }

    // Add a new login
    public void addLogin(Login login) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(login);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    // Edit an existing login
    public void editLogin(Login login) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(login);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    // Delete a login by ID
    public void deleteLogin(Integer loginId) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Login login = (Login) session.load(Login.class, loginId);
            session.delete(login);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    // Get login details by email and password
    public List<Login> getBy(String uEmail, String uPass) {
        List<Login> userList = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Login where email = :uEmail AND password = :uPass");
            query.setString("uEmail", uEmail);
            query.setString("uPass", uPass);
            userList = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return userList;
    }
}
