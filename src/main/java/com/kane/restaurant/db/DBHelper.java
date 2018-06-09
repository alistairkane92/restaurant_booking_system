package com.kane.restaurant.db;

import com.kane.restaurant.models.Booking;
import com.kane.restaurant.models.Customer;
import com.kane.restaurant.models.Table;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

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

    public static void makeBooking(Customer customer, Booking booking, Table table){
        //customer to booking
       customer.addBooking(booking);

       //booking to table
       table.addBooking(booking);

       //persist
        save(booking);
        save(customer);
        save(table);
    }

    public static void addTableToBooking(Table table, Booking booking){
        table.addBooking(booking);
        booking.addTable(table);
        save(booking);
    }

    public static boolean findTable(Customer customer, Calendar date, int numberToSit, String additionalComment) {
        List<Table> allTables = getAll(Table.class);

        for (Table table : allTables){
            if (!table.hasDuplicateBooking(date) && (table.getCapacity() > numberToSit)){
                Booking newBooking = new Booking(customer, numberToSit, date, additionalComment);
                save(newBooking);
                table.addBooking(newBooking);
                save(table);
                save(newBooking);
                return true;
            }
        }
//
//        return false;
//    }
}
