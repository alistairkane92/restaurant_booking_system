package com.kane.restaurant.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name="bookings")
public class Booking {
    private int id;
    private Customer customer;
    private int quantity;
    private Set<Table> tables;
    private Calendar time;

    public Booking() {
    }

    public Booking(Customer booker, int quantity, Calendar bookingTime) {
        this.customer = booker;
        this.quantity = quantity;
        this.time = bookingTime;
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

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer booker) {
        this.customer = booker;
    }

    @Column(name="quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="bookings_and_tables",
//        joinColumns = {@JoinColumn(name = "booking_id")},
//        inverseJoinColumns = {@JoinColumn(name = "table_id")})
//    @LazyCollection(LazyCollectionOption.FALSE)
//    public Set<Table> getTables() {
//        return tables;
//    }

    public void setTables(Set<Table> tables) {
        this.tables = tables;
    }

    @Column(name="time")
    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
