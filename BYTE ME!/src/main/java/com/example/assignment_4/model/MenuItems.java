package com.example.assignment_4.model;

public class MenuItems {
    private String name;
    private String category;  // e.g., "snacks", "beverages", "meals"
    private double price;
    private boolean isAvailable;

    public MenuItems(String name, String category, double price, boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
    public void setCategory(String c1) {
        this.category=c1;
    }
    public void setPrice(double p1) {
        this.price = p1;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean r) {
        this.isAvailable=r;
    }
    @Override
    public String toString() {
        return name + " (" + category + ") - ₹" + price + " - " + (isAvailable ? "Available" : "Not Available");
    }
}

