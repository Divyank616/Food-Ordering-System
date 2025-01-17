package com.example.assignment_4.model;

import java.util.ArrayList;
import java.util.List;


public class Menu {
    private List<MenuItems> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItems item) {
        items.add(item);
    }
    public void removeItems(MenuItems item){
        items.remove(item);
    }

    public List<MenuItems> getItems() {
        return items;
    }
}
