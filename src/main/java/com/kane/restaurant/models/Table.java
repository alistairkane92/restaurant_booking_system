package com.kane.restaurant.models;

import java.util.HashSet;
import java.util.Set;

public class Table {
    private int id;
    private Set<Customer> previouslyBookedCustomers;
    private int capacity;

    public Table() {
    }

    public Table(int capacity) {
        this.capacity = capacity;
        this.previouslyBookedCustomers = new HashSet<Customer>();
    }
}
