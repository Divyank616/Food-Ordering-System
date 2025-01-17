package com.example.assignment_4.Utils;

import com.example.assignment_4.User.Customer;
import com.example.assignment_4.model.Order;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "case.txt";

    // In FileHandler.java
    public static void writeUserData(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Customer customer : customers) {
                // Skip customers with no real orders
                List<Order> validOrders = new ArrayList<>();
                if (customer.gethistory() != null) {
                    for (Order order : customer.gethistory()) {
                        // Only save orders that have valid IDs and status
                        if (order.getId() > 0 && order.getStatus() != null) {
                            validOrders.add(order);
                        }
                    }
                }

                // Only write customer data if they have valid orders
                if (!validOrders.isEmpty()) {
                    writer.write(String.format("EMAIL:%s;STATUS:%s;ORDERS:",
                            customer.getEmail(),
                            customer.getStatus()));

                    // Write only valid orders
                    for (int i = 0; i < validOrders.size(); i++) {
                        Order order = validOrders.get(i);
                        writer.write(String.format("%d:%s:%.2f",
                                order.getId(),
                                order.getStatus(),
                                order.getTotalAmount()));
                        if (i < validOrders.size() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
            }
            System.out.println("User data successfully saved to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
    }
    public static List<Customer> readUserData() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No existing user data found.");
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                Customer customer = parseCustomer(line);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }

        return customers;
    }

    private static Customer parseCustomer(String line) {
        try {
            String[] parts = line.split(";");
            if (parts.length < 2) return null;

            String email = parts[0].split(":")[1];
            String status = parts[1].split(":")[1];

            Customer customer = new Customer(email, status);

            if (parts.length > 2 && parts[2].startsWith("ORDERS:")) {
                String ordersStr = parts[2].substring(7);
                if (!ordersStr.isEmpty()) {
                    String[] orderParts = ordersStr.split(",");
                    for (String orderPart : orderParts) {
                        String[] orderDetails = orderPart.split(":");
                        if (orderDetails.length == 3) {
                            int orderId = Integer.parseInt(orderDetails[0]);
                            String orderStatus = orderDetails[1];
                            double total = Double.parseDouble(orderDetails[2]);
                            // Only add orders with valid IDs
                            if (orderId > 0) {
                                Order order = new Order(orderId, orderStatus, "");
                                order.setTotalAmount(total);
                                customer.history.add(order);
                            }
                        }
                    }
                }
            }

            return customer;
        } catch (Exception e) {
            System.err.println("Error parsing customer data: " + e.getMessage());
            return null;
        }
    }

    public static void showHistory(List<Customer> customers) {
        System.out.println("Displaying Order History for All Users:");
        System.out.println("======================================");

        for (Customer customer : customers) {
            System.out.println("Customer Email: " + customer.getEmail());
            System.out.println("Customer Status: " + customer.getStatus());

            List<Order> orderHistory = customer.gethistory();
            if (orderHistory == null || orderHistory.isEmpty()) {
                System.out.println("No orders found for this customer.");
            } else {
                System.out.println("Order History:");
                for (Order order : orderHistory) {
                    // Only show valid orders
                    if (order.getId() > 0 && order.getStatus() != null) {
                        System.out.println(String.format("  Order ID: %d, Status: %s, total amount: %.2f",
                                order.getId(),
                                order.getStatus(),
                                order.getTotalAmount()));
                    }
                }
            }
            System.out.println("--------------------------------------");
        }
    }





    public static void writePending(List<Order> p) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("case1.txt", false))) {
            for (Order o : p) {
                writer.write(o.toString()); // Ensure the Customer class has a proper toString() implementation
                writer.newLine(); // Separate users by lines for readability
            }
            System.out.println("User data successfully saved to " + "case1.txt");
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
    }

    public static List<Order> readPendings() {
        List<Order> pending_order = new ArrayList<>();
        File file = new File("case1.txt");

        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            System.out.println("No existing user data found.");
            return pending_order; // Return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line1;
            while ((line1 = reader.readLine()) != null) {
                // Assuming you serialize customers in some format (e.g., JSON or custom text)
                // Deserialize or parse the line to create a Customer object
                Order O1 = parseOrder(line1);
                pending_order.add(O1);
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }

        return pending_order;
    }

    // Helper method to parse an order from string
    private static Order parseOrder(String orderData) {
        try {
            String[] parts = orderData.split(", ");

            // Extract and parse order ID
            int orderId = Integer.parseInt(parts[0].split(": ")[1].trim());

            // Extract status
            String status = parts[1].split(": ")[1].trim();

            // Extract total (if needed, though not part of the constructor above)
            double total = Double.parseDouble(parts[2].split(": ")[1].trim());

            // Extract items
            String itemsPart = orderData.substring(orderData.indexOf("Items: ") + 7).trim();

            // Log parsed values
            System.out.println("Parsed Order -> ID: " + orderId + ", Status: " + status + ", Items: " + itemsPart);

            // Create and return the Order object
            return new Order(orderId, status, itemsPart);
        } catch (Exception e) {
            System.err.println("Error parsing order data: \"" + orderData + "\" - " + e.getMessage());
            return null;
        }
    }

}
