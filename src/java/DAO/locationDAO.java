package DAO;

import pojo.NailartUtil;
import pojo.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

public class locationDAO {

    // Retrieve all locations from the database
    public List<Location> retrieveTblLocations() {
        List<Location> listLocation = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Location"); // No type specified
            listLocation = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
        return listLocation;
    }

    // Get location by ID
    public List<Location> getById(Integer id) {
        List<Location> listLocations = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Location where id = :id"); // No type specified
            query.setParameter("id", id);
            listLocations = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
        return listLocations;
    }

    // Method to add a new location
    public void addLocation(Location location) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    // Method to edit a location
    public void editLocation(Location location) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    // Method to delete a location
    public void deleteLocation(Integer idLocation) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Location location = (Location) session.load(Location.class, idLocation);
            session.delete(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
    }
}
