package com.example.assignment_4.User;

import com.example.assignment_4.model.Menu;
import com.example.assignment_4.model.MenuItems;
import com.example.assignment_4.model.Order;

import java.util.*;
import java.util.List;

public class Admin extends user{


    public Admin(String email) {
        super(email);
    }

    // Login method overridden from User class
    @Override
    public void login() {
        System.out.println("Administrator has logged in.");
    }
    public void Add_new_Items(Menu menu, String name,String cat, double p1,boolean av){
        MenuItems m1=new MenuItems(name,cat,p1,av);
        menu.addItem(m1);
        System.out.println(m1.getName()+ " Successfully added to menu");
    }
    public void Update_Item(Menu menu, String tochange, String M2){
        Scanner sc=new Scanner(System.in);
        for(MenuItems m: menu.getItems()){
            if(m.getName().equalsIgnoreCase(M2)) {
                if (tochange.equalsIgnoreCase("price")) {
                    System.out.print("New Price you want to replace it with: ");
                    double response;
                    response = sc.nextDouble();
                    m.setPrice(response);
                    System.out.println("Price successfully updated for "+m.getName());
                }
                if (tochange.equalsIgnoreCase("category")) {
                    System.out.print("New Category you want to replace it with: ");
                    String response1;
                    response1 = sc.nextLine();
                    m.setCategory(response1);
                    System.out.println("Category successfully updated for "+m.getName());
                }
                if (tochange.equalsIgnoreCase("availability")) {
                    System.out.print("New availability you want to replace it with: ");
                    boolean response2;
                    response2 = sc.nextBoolean();
                    m.setAvailable(response2);
                    System.out.println("Availability successfully updated for "+m.getName());

                }
            }
        }
    }
    public void Remove_Items(Menu menu, MenuItems m1, List<Order> Pendings){
        menu.removeItems(m1);
        System.out.println(m1.getName()+ " Successfully removed to menu");
        if (Pendings.contains(m1)){
            Pendings.remove(m1);
            System.out.println("This order cannot be served any more, Sorry for Inconvenience");
        }
    }
    public void Display_Pending_list(List<Order> Pendings) {
        for (Order o : Pendings) {
            System.out.println("Ordered by ID " + o.getId() + " order of items " + o.displayMap1(o.itemList));
        }
    }

    public void managePendingList(List<Order> Pendings) {
        List<Order> vipOrders = new ArrayList<>();
        List<Order> regularOrders = new ArrayList<>();

        // Separate VIP and regular orders
        for (Order order : Pendings) {
            if (order.isVipOrder()) {
                vipOrders.add(order);
            } else {
                regularOrders.add(order);
            }
        }
        Pendings.clear();
        Pendings.addAll(vipOrders);
        Pendings.addAll(regularOrders);
    }
    public void Update_order_status(List<Order> Pendings, List<Order> Processing) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Order> iterator = Pendings.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            System.out.println(order.getId() + " Should proceed to prepare? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                // Move the order to the processing list
                Processing.add(order);
                order.setStatus("Processing");
                iterator.remove(); // Safely remove from pending list
            }
        }
    }
    public void Check_Processing_list(List<Order> Processing, List<Order> Done){
        Scanner scanner = new Scanner(System.in);
        Iterator<Order> iterator1 = Processing.iterator();

        while (iterator1.hasNext()) {
            Order order = iterator1.next();
            System.out.println(order.getId() + " is prepared? (yes/no)");
            String response1 = scanner.nextLine();

            if (response1.equalsIgnoreCase("yes")) {
                // Move the order to the processing list
                Done.add(order);
                order.setStatus("Served!");
                iterator1.remove(); // Safely remove from pending list
            }
        }
    }
    public HashMap<String, String> Issue_handling(ArrayList<String> Issues) {
        HashMap<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        for (String key : Issues) {
            System.out.print("Enter a response for this issue; " + key + " : ");
            String value = scanner.nextLine();
            map.put(key, value);
        }
        return map;
    }
    public void displayMap(HashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("\"" + entry.getKey() + "\" is solved as \"" + entry.getValue() + "\"");
        }
    }
    public void Report_generation(List<Order> Done){
        double totalprice=0.0;
        int total=0;
        for(Order o3: Done){
            totalprice+=o3.getTotalAmount();
            total++;
        }
        System.out.println("REPORT: There are total "+total+" made and total sales of rupees ₹"+totalprice);
    }

}
