package com.example.assignment_4.model;

import com.example.assignment_4.model.MenuItems;

public class CartItem {
    private MenuItems menuItem; // Reference to the MenuItem
    private int quantity;

    public CartItem(MenuItems menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItems getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return menuItem.getName() + " x " + quantity + " - Total: $" + getTotalPrice();
    }
}
