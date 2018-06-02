package com.kane.restaurant.models;

import java.util.Set;

public class Table {
    private int id;
    private Set<Customer> customers;
    private int capacity;

    public Table(int capacity) {
        this.capacity = capacity;
    }
}
