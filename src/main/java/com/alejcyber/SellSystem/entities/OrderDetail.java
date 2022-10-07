package com.alejcyber.SellSystem.entities;

import javax.persistence.*;
@Entity
@Table(name="`order_detail`")
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="amount")
    private Double amount;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    public OrderDetail() {}

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail [amount=" + amount + ", id=" + id + ", order=" + order + ", product=" + product
                + ", quantity=" + quantity + "]";
    }
}