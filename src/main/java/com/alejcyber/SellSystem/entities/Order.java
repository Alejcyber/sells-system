package com.alejcyber.SellSystem.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="`order`")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="date")
    private Date date;

    @Column(name="price")
    private Double total;

    @ManyToOne
    private User buyer;
    
    public Order() {}

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "OrderDetail [buyer=" + buyer + ", date=" + date + ", id=" + id + ", total=" + total + "]";
    }
}