/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojo.NailartUtil;
import pojo.Reservation;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Aspire7
 */
public class ReservationDAO {

    public List<Reservation> retrieveTblReservation() {
        List listReservation = new ArrayList();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Reservation");
            listReservation = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReservation;
    }

    public List<Reservation> getbyID(String idReservation) {
        Reservation reservation = new Reservation();
        List<Reservation> listReservation = new ArrayList();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Reservation where id = :id");
            query.setString("id", idReservation);
            reservation = (Reservation) query.uniqueResult();
            listReservation = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReservation;
    }  
    
    public List<Reservation> searchReservation(String id, String phone, String email) {
    List<Reservation> listReservation = new ArrayList<>();
    Transaction transaction = null;
    Session session = NailartUtil.getSessionFactory().openSession();

    try {
        transaction = session.beginTransaction();
        String queryString = "from Reservation where 1=1"; // Initialize query

        // Dynamically build the query based on non-null parameters
        if (id != null && !id.isEmpty()) {
            queryString += " and id = :id";
        }
        if (phone != null && !phone.isEmpty()) {
            queryString += " and phone = :phone";
        }
        if (email != null && !email.isEmpty()) {
            queryString += " and email = :email";
        }

        Query query = session.createQuery(queryString);

        // Set the query parameters dynamically
        if (id != null && !id.isEmpty()) {
            query.setString("id", id);
        }
        if (phone != null && !phone.isEmpty()) {
            query.setString("phone", phone);
        }
        if (email != null && !email.isEmpty()) {
            query.setString("email", email);
        }

        listReservation = query.list();
        transaction.commit();
    } catch (Exception e) {
        System.out.println(e);
    }
    return listReservation;
}


    public void addReservation(Reservation reservation) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editUser(Reservation reservation) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(reservation);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteUser(Integer idReservation) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Reservation reservation = (Reservation) session.load(Reservation.class, new Integer(idReservation));
            session.delete(reservation);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
        public List<Reservation> ShowByID(int id) {
        List<Reservation> usersList = new ArrayList<Reservation>();

        Transaction trans = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Reservation where id=:id");
            query.setInteger("id", id);
            usersList = query.list();
            trans.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usersList;
    }
      
}
