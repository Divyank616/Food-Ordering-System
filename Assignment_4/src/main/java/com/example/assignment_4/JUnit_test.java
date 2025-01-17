package com.example.assignment_4;

import com.example.assignment_4.model.MenuItems;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

import com.example.assignment_4.model.*;
import com.example.assignment_4.User.*;
import com.example.assignment_4.Utils.*;
//import com.example.assignment_4.main.*;


public class JUnit_test {

    @Test
    public void testOrderingOutOfStockItem() {
        // Setup
        MenuItems outOfStockItem = new MenuItems("Pizza", "Snacks", 90.0, false); // Stock is 0
        Customer customer = new Customer("John CENA","non-vip"); // Create a customer

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.Add_item_in_Cart(outOfStockItem, 1); // Attempt to add out-of-stock item
        });

        // Assert
        assertEquals("Item is out of stock!", exception.getMessage());
        assertTrue(customer.getCart().isEmpty()); // Ensure cart is still empty
    }
    @Test
    public void testCartOperations() {
        // Setup
        MenuItems tea = new MenuItems("Tea", "Beverage", 10.0, true); // Available in stock
        MenuItems samosa = new MenuItems("Samosa", "Snacks", 15.0, true); // Available in stock
        Customer customer = new Customer("TheRock", "non-vip"); // Create a customer

        // Add item to cart
        customer.Add_item_in_Cart(tea, 2); // Add 2 units of Tea
        assertEquals(20.0, customer.viewCartTotal(), 0.01); // Verify total price

        // Modify quantity of an item
        customer.updateCartItemQuantity(tea, 3); // Update Tea to 3 units
        assertEquals(30.0, customer.viewCartTotal(), 0.01); // Verify total price

        // Add another item
        customer.Add_item_in_Cart(samosa, 1); // Add 1 unit of Samosa
        assertEquals(45.0, customer.viewCartTotal(), 0.01); // Verify total price

        // Attempt to set a negative quantity
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.updateCartItemQuantity(tea, -1);
        });

        assertEquals("Quantity cannot be negative!", exception.getMessage());
        assertEquals(45.0, customer.viewCartTotal(), 0.01); // Ensure price remains unchanged
    }

    @Test
    public void testViewCartTotal() {
        // Setup
        MenuItems tea = new MenuItems("Tea", "Beverage", 10.0, true);
        MenuItems samosa = new MenuItems("Samosa", "Snacks", 15.0, true);
        Customer customer = new Customer("jane foster", "non-vip");

        // Add items to cart
        customer.Add_item_in_Cart(tea, 2); // Add 2 Teas (20.0)
        customer.Add_item_in_Cart(samosa, 3); // Add 3 Samosas (45.0)

        // Act
        double total = customer.viewCartTotal();

        // Assert
        assertEquals(65.0, total, 0.01); // Verify total price is correct
    }

    @Test
    public void testViewCartTotalEmptyCart() {
        // Setup
        Customer customer = new Customer("pane Doe", "non-vip");

        // Act
        double total = customer.viewCartTotal();

        // Assert
        assertEquals(0.0, total, 0.01); // Empty cart should return 0.0
    }
}