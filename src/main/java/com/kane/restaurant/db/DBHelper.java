package com.kane.restaurant.db;

import com.kane.restaurant.models.Booking;
import com.kane.restaurant.models.Customer;
import com.kane.restaurant.models.Table;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class DBHelper {
    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = db.HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteAll(Class classType) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            results = cr.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> List<T> getAllByAscending(String property, Class classType) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            cr.addOrder(Order.asc(property));
            results = cr.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T find(int id, Class classType) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        T result = null;
        try {
            Criteria cr = session.createCriteria(classType);
            cr.add(Restrictions.eq("id", id));
            result = (T) cr.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(result);
        return result;
    }

    public static void addTableToBooking(Table table, Booking booking){
        table.addBooking(booking);
        booking.addTable(table);
        save(booking);
    }

    public static boolean makeBooking(Booking booking) {
        List<Table> allTables = getAllByAscending("capacity", Table.class);
        int numberToSit = booking.getQuantity();

        //check for an appropriately sized table
        for (Table table : allTables){
            if (!table.hasDuplicateBooking(booking.getDate())) {
                if (table.getCapacity() >= numberToSit){
                    addTableToBooking(table, booking);
                    numberToSit -= table.getCapacity();
                    if (numberToSit <= 0) return true;
                }
            }
        }

        return false;
    }

    public static List<Booking> getBookingsByDate(Calendar calendar) {
        session = db.HibernateUtil.getSessionFactory().openSession();
        List<Booking> todaysBookings = new ArrayList<>();
        List<Booking> allBookings = getAllByAscending("date", Booking.class);

        for (Booking booking : allBookings){
            if (calendar.get(Calendar.YEAR) == booking.getDate().get(Calendar.YEAR)){
                if (calendar.get(Calendar.MONTH) == booking.getDate().get(Calendar.MONTH)){
                    if (calendar.get(Calendar.DAY_OF_MONTH) == booking.getDate().get(Calendar.DAY_OF_MONTH)){
                        todaysBookings.add(booking);
                    }
                }
            }
        }

        return todaysBookings;
    }
}
