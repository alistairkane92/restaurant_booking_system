package com.kane.restaurant.models;

public class Customer {
    private int id;
    private String name;
    private Set<Table> tables;
    private Set<Booking> bookings;

    public Customer(String name) {
        this.name = name;
    }

    
}
