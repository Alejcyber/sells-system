package com.alejcyber.SellSystem.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="`product`", 
uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User seller;
    
    public Product() {}

    public Product(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [category=" + category + ", id=" + id + ", name=" + name + ", price=" + price + ", seller="
                + seller + "]";
    }
}