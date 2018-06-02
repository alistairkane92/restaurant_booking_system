package com.kane.restaurant.models;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Set<Booking> bookings;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.email = email;
        this.name = name;
        this.bookings = new HashSet<Booking>();
    }


}
