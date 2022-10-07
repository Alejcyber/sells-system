package com.alejcyber.SellSystem.utils;


import java.util.ArrayList;
import java.util.List;

import com.alejcyber.SellSystem.entities.Product;
import com.alejcyber.SellSystem.entities.User;

public class CartInfo {

    private int orderNum;

    private User buyer;

    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }

    private CartLineInfo findLineById(Long id) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProduct().getId() == id) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(Product product, int quantity) {
        CartLineInfo line = this.findLineById(product.getId());

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProduct(product);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateProduct(Long id, int quantity) {
        CartLineInfo line = this.findLineById(id);

        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(Product product) {
        CartLineInfo line = this.findLineById(product.getId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidBuyer() {
        return this.buyer != null;
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateProduct(line.getProduct().getId(), line.getQuantity());
            }
        }

    }

}