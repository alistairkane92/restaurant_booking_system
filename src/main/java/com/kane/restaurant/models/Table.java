package com.kane.restaurant.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@javax.persistence.Table(name="tables")

public class Table {
    private int id;
    private Set<Booking> bookings;
    private int capacity;
    private int tableNumber;

    public Table() {
    }

    public Table(int capacity, int tableNumber) {
        this.capacity = capacity;
        this.bookings = new HashSet<Booking>();
        this.tableNumber = tableNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="bookings_and_tables",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "booking_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Column(name="capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name="tableNumber")
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean hasDuplicateBooking(Calendar date){
        for (Booking booking : this.bookings){
            if (booking.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public void addBooking(Booking newBooking){
        this.bookings.add(newBooking);
    }
}
