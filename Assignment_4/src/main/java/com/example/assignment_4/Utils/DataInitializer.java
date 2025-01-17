package com.example.assignment_4.Utils;

import com.example.assignment_4.model.*;
import com.example.assignment_4.main.*;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static List<Order> Pendings = new ArrayList<>();
    public static Menu initializeMenu() {
        Menu menu = new Menu();

        menu.addItem(new MenuItems("Burger", "Snacks", 40, true));
        menu.addItem(new MenuItems("Pasta", "Snacks", 45, true));
        menu.addItem(new MenuItems("Biscuits", "Wafer", 20, true));
        menu.addItem(new MenuItems("Chocolate", "Wafer", 50, true));
        menu.addItem(new MenuItems("Rajma-Chawal", "Lunch", 80, true));
        menu.addItem(new MenuItems("Dosa", "South-Indian", 75, true));
        menu.addItem(new MenuItems("Biryani", "Lunch", 65, true));
        menu.addItem(new MenuItems("Idli", "South-Indian", 30, true));
        menu.addItem(new MenuItems("Pizza", "Snacks", 90, true));
        menu.addItem(new MenuItems("Bread pakoda", "Snacks", 20, true));
        menu.addItem(new MenuItems("Samosa", "Snacks", 15, true));
        menu.addItem(new MenuItems("Tea", "Beverage", 10, true));
        menu.addItem(new MenuItems("Coffee", "Beverage", 40, true));
        menu.addItem(new MenuItems("Shake", "Beverage", 60, true));
        menu.addItem(new MenuItems("Lassi", "Beverage", 40, true));
        menu.addItem(new MenuItems("Chhole Bhature", "Lunch", 50, true));
        menu.addItem(new MenuItems("Pav Bhaji", "Lunch", 50, true));
        menu.addItem(new MenuItems("Chips", "Wafer", 20, true));
        menu.addItem(new MenuItems("Sambhar-Vada", "South-Indian", 35, true));

        return menu;
    }

}

