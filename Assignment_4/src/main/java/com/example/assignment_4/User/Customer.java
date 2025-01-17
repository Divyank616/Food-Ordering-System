package com.example.assignment_4.User;

import com.example.assignment_4.model.Menu;
import com.example.assignment_4.model.MenuItems;
import com.example.assignment_4.model.Order;
import com.example.assignment_4.User.*;
import com.example.assignment_4.model.CartItem;

import java.util.*;

public class Customer extends user {

    public List<Order> history;
    protected Map<MenuItems, Integer> Cart; // Map to store items and their quantities

    public Customer(String email, String status) {
        super(email, status);
        this.history = new ArrayList<>();
        this.Cart = new HashMap<>();
    }

    public void login() {
        System.out.println("Login successful for " + email);
    }

    public List<Order> gethistory() {
        return history;
    }

    public void Add_item_in_Cart(MenuItems M1, int qty) {
        if (M1.isAvailable() == false) {
            throw new IllegalArgumentException("Item is out of stock!");
        }
        Cart.put(M1,qty);
        System.out.println(M1.getName()+" x "+qty+" Added item to cart");
    }

    public void removeItemFromCart(MenuItems menuItem) {
        if (Cart.remove(menuItem) != null) {
            System.out.println(menuItem.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public double viewCartTotal() {
        return Cart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void checkout(List<Order> Pendings) {
        if (Cart.isEmpty()) {
            System.out.println("Cart is empty. Add items before checking out.");
            return;
        }

        // Create a new order using the cart items and total
        Order order = new Order(generateOrderId(Pendings), this.getEmail(), "pending", new HashMap<>(Cart), viewCartTotal(),getStatus());
        this.history.add(order);
        Pendings.add(order);

        // Clear the cart after checkout
        Cart.clear();
        System.out.println("Checkout successful. Order placed with ID: " + order.getId());
    }
    private int generateOrderId(List<Order> Pendings) {
        return Pendings.size() + 1;
    }

    public void Look_Menu(Menu menu) {
        System.out.println("Items available currently out of all items in menu:");
        for (MenuItems menuItems : menu.getItems()) {
            if (menuItems.isAvailable() == true) {
                System.out.println(menuItems);
            }
        }
    }
    public void updateCartItemQuantity(MenuItems menuItem, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        if (Cart.containsKey(menuItem)) {
            Cart.put(menuItem, quantity);
            System.out.println("Updated " + menuItem.getName() + " quantity to " + quantity);
        }
        else {
            System.out.println("Item not found in cart.");
        }
    }
    public Map<MenuItems,Integer> getCart(){
        return this.Cart;
    }

    public void cancelOrder(List<Order> Pendings) {
        // Check if the order status is "pending"
        Scanner ss=new Scanner(System.in);
        if (history.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("Order History:");
        for (Order order : history) {
            System.out.println("Order ID: " + order.getId() + ", Status: " + order.getStatus() + ", Total: ₹" + order.getTotalAmount());
        }
        System.out.print("which order do you want to Cancel?(give Order ID): ");
        int IDi=ss.nextInt();
        Order Odi=findOrderByID(history,IDi);
        if (!Odi.getStatus().equals("pending")) {
            System.out.println("This order has already started preparing. You can't cancel it!");
            return;
        }
        else {
            history.remove(Odi);

            if (Pendings != null) {
                Pendings.remove(Odi);
            }

            System.out.println("Order cancelled successfully");
        }
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public void setHistory(List<Order> existingHistory) {
        this.history = new ArrayList<>(existingHistory);
    }

    public List<MenuItems> searchItems(Menu menu, String keyword) {
        List<MenuItems> searchResults = new ArrayList<>();
        for (MenuItems item : menu.getItems()) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }
    public void displaySearchResults(List<MenuItems> M4) {
        if (M4.isEmpty()) {
            System.out.println("No items found by this name");
        }
        else {
            for (MenuItems item : M4) {
                System.out.println(item);
            }
        }
    }
    public List<MenuItems> filterByCategory(Menu menu, String category) {
        List<MenuItems> filteredItems = new ArrayList<>();
        for (MenuItems item : menu.getItems()) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
    public void sortByPrice(Menu menu) {
        Collections.sort(menu.getItems(), Comparator.comparingDouble(MenuItems::getPrice));
        System.out.println("Menu sorted by price (ascending):");
        for (MenuItems item : menu.getItems()) {
            System.out.println(item);
        }
    }
    // View all orders placed by this customer
    public void viewOrderHistory() {
        if (history.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("Order History:");
        for (Order order : history) {
            System.out.println("Order ID: " + order.getId() + ", Status: " + order.getStatus() + ", Total: ₹" + order.getTotalAmount());
        }
    }

    // Track specific order by ID
    public void trackOrder(int orderId) {
        for (Order order : history) {
            if (order.getId() == orderId) {
                System.out.println("Tracking Order ID: " + orderId);
                order.displayOrderDetails();
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found.");
    }
    public Order findOrderByID(List<Order> history, int ID) {
        for (Order OO : gethistory()) {
            if (OO.getId()==(ID)) {
                return OO;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Customer Email: " + email + "\nCustomer Status: " + status;
    }


}

