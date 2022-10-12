package com.alejcyber.SellSystem.controllers.forms;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductForm {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductForm [categoryId=" + categoryId + ", id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}
