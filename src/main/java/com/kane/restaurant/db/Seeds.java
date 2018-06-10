package com.kane.restaurant.db;

import com.kane.restaurant.models.Booking;
import com.kane.restaurant.models.Customer;
import com.kane.restaurant.models.Table;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Table.class);
        DBHelper.deleteAll(Booking.class);
        DBHelper.deleteAll(Customer.class);

        Customer customer1 = new Customer("Ally", "ally@codeclan.com");
        DBHelper.save(customer1);
        Customer customer2 = new Customer("Alan", "alan@codeclan.com");
        DBHelper.save(customer2);
        Customer customer3 = new Customer("Upul", "upul@codeclan.com");
        DBHelper.save(customer3);
        Customer customer4 = new Customer("Louise", "louise@codeclan.com");
        DBHelper.save(customer4);

        Table table1 = new Table(4, 1);
        DBHelper.save(table1);
        Table table2 = new Table(4, 2);
        DBHelper.save(table2);
        Table table3 = new Table(2, 3);
        DBHelper.save(table3);
        Table table4 = new Table(4, 4);
        DBHelper.save(table4);
        Table table5 = new Table(4, 5);
        DBHelper.save(table5);
        Table table6 = new Table(6, 6);
        DBHelper.save(table6);
        Table table7 = new Table(12, 7);
        DBHelper.save(table7);

        GregorianCalendar date1 = new GregorianCalendar(2018, Calendar.OCTOBER, 20, 8, 0);
        GregorianCalendar date2 = new GregorianCalendar(2018, Calendar.JUNE, 9, 7, 30);
        GregorianCalendar date3 = new GregorianCalendar(2018, Calendar.JUNE, 9, 9, 30);
        GregorianCalendar date4 = new GregorianCalendar(2018, Calendar.JUNE, 10, 9, 30);

        Booking booking1 = new Booking(customer1, 2, date1, "Peanut allergy");
        DBHelper.save(booking1);

        Booking booking2 = new Booking(customer1, 4, date2, "");
        DBHelper.save(booking2);

        Booking booking3 = new Booking(customer1, 6, date3, ".");
        DBHelper.save(booking3);

        Booking booking4 = new Booking(customer1, 6, date4, ".");
        DBHelper.save(booking4);

        DBHelper.makeBooking(booking1);
        DBHelper.makeBooking(booking2);
        DBHelper.makeBooking(booking3);
    }
}
