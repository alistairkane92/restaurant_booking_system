package com.kane.restaurant.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@javax.persistence.Table(name="tables")

public class Table {
    private int id;
    private Set<Booking> bookings;
    private int capacity;

    public Table() {
    }

    public Table(int capacity) {
        this.capacity = capacity;
        this.bookings = new HashSet<Booking>();
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean hasDuplicateBooking(Calendar date){
        for (Booking booking : this.bookings){
            if (booking.getTime().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public void addBooking(Booking newBooking){
        this.bookings.add(newBooking);
    }
}
