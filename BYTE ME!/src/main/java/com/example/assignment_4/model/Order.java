package com.example.assignment_4.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int id;
    private String Category;
    private String owner;
    private String status;
    public HashMap<MenuItems, Integer> itemList;
    private double totalAmount;
    public double temp_price;
    public int temp_qty;
    public String temp_name;
    public String temp_status;
    public int temp_id;

    public Order(int id, String owner, String status, HashMap<MenuItems, Integer> itemList, double totalAmount, String category) {
        this.id = id;
        this.owner = owner;
        this.status = status;
        this.itemList = itemList;
        this.totalAmount = totalAmount;
        this.Category=category;
    }
    public Order(String foodtem, int qty, double pp,String st) {
        this.temp_name= foodtem;
        this.temp_qty = qty;
        this.temp_price=pp;
        this.temp_status=st;
    }
    public Order(int idd, String stt, String ffdd) {
        this.temp_id= idd;
        this.temp_name= ffdd;
        this.temp_status=stt;
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }
    public String displayMap1(Map<MenuItems, Integer> itemList) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<MenuItems, Integer> entry : itemList.entrySet()) {
            result.append(entry.getKey().getName())
                    .append(" x ")
                    .append(entry.getValue())
                    .append(", ");
        }
        // Remove the trailing comma and space
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public boolean isVipOrder(){
        if(Category.equalsIgnoreCase("VIP")) {
            return true;
        }
        else{
            return false;
        }
    }

    // Method to display order details
    public void displayOrderDetails() {
        System.out.println("Order ID: " + id);
        System.out.println("Owner: " + owner);
        System.out.println("Status: " + status);
        System.out.println("Items: " + itemList);
        System.out.println("Total Amount: ₹" + totalAmount);
    }

    // Method to mark order as completed
    public void completeOrder() {
        if ("pending".equalsIgnoreCase(status)) {
            status = "completed";
            System.out.println("Order " + id + " has been completed.");
        } else {
            System.out.println("Order " + id + " cannot be completed as it is not pending.");
        }
    }

    // Method to cancel order if pending
    public void cancelOrder() {
        if ("pending".equalsIgnoreCase(status)) {
            status = "cancelled";
            System.out.println("Order " + id + " has been cancelled.");
        }
        else {
            System.out.println("Order " + id + " cannot be cancelled as it is not pending.");
        }
    }
    public HashMap<MenuItems, Integer> getCartItems() {
        return itemList;
    }
    @Override
    public String toString() {
        return "Order ID: " + this.getId() +
                ", Status: " + this.getStatus() +
                ", Total: " + this.getTotalAmount() +
                ", Items: " + this.itemList; // Ensure items are formatted neatly
    }
}

