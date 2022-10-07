package com.alejcyber.SellSystem.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="`category`", 
uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;
    
    public Category() {}

    public Category(String name) {
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

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}