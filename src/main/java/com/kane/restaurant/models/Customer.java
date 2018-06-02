package com.kane.restaurant.models;

import java.util.Set;

public class Customer {
    private int id;
    private String name;
    private Set<Table> tables;
    private Set<Booking> bookings;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }


}
