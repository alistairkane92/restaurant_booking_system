package com.kane.restaurant.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@javax.persistence.Table(name="bookings")
public class Booking {
    private int id;
    private Customer customer;
    private int quantity;
    private Set<com.kane.restaurant.models.Table> tables;
    private Calendar date;
    private String additionalComment;

    public Booking() {
    }

    public Booking(Customer booker, int quantity, Calendar bookingTime, String additionalComment) {
        this.customer = booker;
        this.quantity = quantity;
        this.date = bookingTime;
        this.additionalComment = additionalComment;
        this.tables = new HashSet<>();
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="bookings_and_tables",
        joinColumns = {@JoinColumn(name = "booking_id")},
        inverseJoinColumns = {@JoinColumn(name = "table_id")})
    public Set<com.kane.restaurant.models.Table> getTables() {
        return tables;
    }

    public void setTables(Set<com.kane.restaurant.models.Table> tables) {
        this.tables = tables;
    }

    @Column(name="date")
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar time) {
        this.date = time;
    }

    @Column(name="comments")
    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public String formatDate(){
        return this.getDate().get(Calendar.HOUR_OF_DAY) + ":" + this.getDate().get(Calendar.MINUTE);
    }

    public void addTable(com.kane.restaurant.models.Table table) {
        this.tables.add(table);
    }
}
