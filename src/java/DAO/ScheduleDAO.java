package DAO;

import pojo.Schedule;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.NailartUtil;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {

    // Retrieve all schedules
    public List<Schedule> retrieveTblSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        Session session = null;
        try {
            session = NailartUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Schedule");
            scheduleList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scheduleList;
    }

    // Get Schedule by ID
    public List<Schedule> getByID(int scheduleId) {
        List<Schedule> scheduleList = new ArrayList<>();
        Session session = null;
        try {
            session = NailartUtil.getSessionFactory().openSession(); // Membuka session
            Query query = session.createQuery("from Schedule where id = :id"); // Query untuk mengambil data berdasarkan ID
            query.setParameter("id", scheduleId); // Set parameter ID
            scheduleList = query.list(); // Ambil hasil query
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Tutup session
            }
        }
        return scheduleList;
    }

    // Search Schedule (by ID, time, or services)
    public List<Schedule> searchSchedule(String id, String time, String services) {
        List<Schedule> scheduleList = new ArrayList<>();
        Session session = null;
        try {
            session = NailartUtil.getSessionFactory().openSession();
            String queryString = "from Schedule where 1=1";
            if (id != null && !id.isEmpty()) queryString += " and id = :id";
            if (time != null && !time.isEmpty()) queryString += " and time = :time";
            if (services != null && !services.isEmpty()) queryString += " and services = :services";

            Query query = session.createQuery(queryString);
            if (id != null && !id.isEmpty()) query.setParameter("id", Integer.parseInt(id));
            if (time != null && !time.isEmpty()) query.setParameter("time", time);
            if (services != null && !services.isEmpty()) query.setParameter("services", services);

            scheduleList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scheduleList;
    }

    // Add Schedule
    public void addSchedule(Schedule schedule) {
        Session session = null;
        try {
            session = NailartUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(schedule);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Edit Schedule
    public void editSchedule(Schedule schedule) {
        Session session = null;
        try {
            session = NailartUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(schedule); // Update data di database
            transaction.commit(); // Commit transaksi
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Tutup session
            }
        }
    }

    // Delete Schedule
    public void deleteSchedule(int scheduleId) {
    Session session = null;
    try {
        session = NailartUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Load the schedule object and delete it
        Schedule schedule = (Schedule) session.get(Schedule.class, scheduleId);
        if (schedule != null) {
            session.delete(schedule);
        }

        transaction.commit();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
}

    
    
}
