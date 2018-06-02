package com.kane.restaurant.models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class Booking {
    private int id;
    private Customer booker;
    private int quantity;
    private Set<Table> tables;
    private Calendar time;

    public Booking() {
    }

    public Booking(Customer booker, int quantity, Calendar bookingTime) {
        this.booker = booker;
        this.quantity = quantity;
        this.time = bookingTime;
    }

}
