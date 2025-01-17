package com.example.assignment_4.main;

import com.example.assignment_4.Utils.*;
import com.example.assignment_4.model.Menu;
import com.example.assignment_4.model.MenuItems;
import com.example.assignment_4.model.Order;
import com.example.assignment_4.User.Admin;
import com.example.assignment_4.User.Customer;

import java.util.*;
import java.util.List;


import static com.example.assignment_4.Utils.FileHandler.showHistory;

public class Main {
    public static void main(String[] args) {

        List<Order> Processing = new ArrayList<>();
        List<Order> Done = new ArrayList<>();
        List<Admin> Admin_history = new ArrayList<>();
        List<Customer> Customer_history = new ArrayList<>();
        ArrayList<String> Issues = new ArrayList<>();
        List<Customer> customerHistory = FileHandler.readUserData();

        Menu menu=DataInitializer.initializeMenu();

        System.out.println("Welcome to the CANTEEN System!");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSelect Role:\n 1. Admin\n 2. Customer\n 3. Show history\n 4. Write down to file \n 5. Exit");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt

            switch (roleChoice) {
                case 1:
                    System.out.print("Enter your email: ");
                    String Email = scanner.nextLine();
                    Admin admin = new Admin(Email);
                    admin.login();
                    while (true) {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. Menu management\n2. Order Management\n3. Report generation\n4. Issues Handling\n5. Exit");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (adminChoice == 1) {
                            System.out.println("a. Add new items\nb. Update existing items\nc. Remove Existing item");
                            String adminChoicerChoice = scanner.nextLine();
                            scanner.nextLine();
                            if (adminChoicerChoice.equalsIgnoreCase("a")) {
                                System.out.print("Enter name of the MenuItem you want to add : ");
                                String Name;
                                Name = scanner.nextLine();
                                System.out.print("Enter Category this item belongs to : ");
                                String cat;
                                cat = scanner.nextLine();
                                System.out.print("Enter Price of the MenuItem you want to add : ");
                                double p;
                                p = scanner.nextDouble();
                                System.out.print("Is this available right now?(true/false): ");
                                boolean t;
                                t = scanner.nextBoolean();
                                admin.Add_new_Items(menu, Name, cat, p, t);
                            } else if (adminChoicerChoice.equalsIgnoreCase("b")) {
                                System.out.print("Enter the name of menu item, whose details you want to Update : ");
                                String toUpdate;
                                toUpdate = scanner.nextLine();
                                System.out.print("What do you want to change(price/category/Availability) : ");
                                String toUpdateCat;
                                toUpdateCat = scanner.nextLine();
                                admin.Update_Item(menu, toUpdateCat, toUpdate);
                            } else if (adminChoicerChoice.equalsIgnoreCase("c")) {
                                System.out.print("Enter the name of menu item You want to delete : ");
                                String toremove;
                                toremove = scanner.nextLine();
                                MenuItems toremove1 = findMenuItemByNAME(menu, toremove);
                                admin.Remove_Items(menu, toremove1,DataInitializer.Pendings);

                            } else {
                                System.out.println("Invalid Entry!");
                            }
                        } else if (adminChoice == 2) {
                            System.out.println("a. View pending list\nb. Update Order Status\nc. Update Processing list\nd. Handle Issues");
                            String adminChoicerChoice1 = scanner.nextLine();
                            if (adminChoicerChoice1.equalsIgnoreCase("a")) {
                                admin.managePendingList(DataInitializer.Pendings);
                                System.out.println("Pending list:");
                                admin.Display_Pending_list(DataInitializer.Pendings);
                            } else if (adminChoicerChoice1.equalsIgnoreCase("b")) {
                                admin.Update_order_status(DataInitializer.Pendings,Processing);
                            } else if (adminChoicerChoice1.equalsIgnoreCase("c")) {
                                admin.Check_Processing_list(Processing,Done);
                            } else if (adminChoicerChoice1.equalsIgnoreCase("d")) {
                                HashMap<String, String> map1 = admin.Issue_handling(Issues);
                                admin.displayMap(map1);
                            }
                        } else if (adminChoice == 3) {
                            admin.Report_generation(Done);
                        } else if (adminChoice == 4) {
                            HashMap<String, String> mp1 = admin.Issue_handling(Issues);
                            admin.displayMap(mp1);
                        } else if (adminChoice == 5) {
                            Admin_history.add(admin);
                            System.out.println("logging out.....");
                            break;
                        } else {
                            System.out.println("Invalid Entry");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your status: ");
                    String status = scanner.nextLine();

                    Customer customer = null;
                    for (Customer existingCustomer : customerHistory) {
                        if (existingCustomer.getEmail().equals(email)) {
                            customer = existingCustomer;
                            System.out.println("Welcome back, " + email + "!");
                            break;
                        }
                    }

                    if (customer == null) {
                        customer = new Customer(email, status);
                        customerHistory.add(customer);
                        System.out.println("Welcome, new customer!");
                    }

                    customer.login();

                    // Proceed with customer menu and operations
                    boolean customerRunning = true;
                    while (customerRunning) {
                        System.out.println("\nCustomer Menu:");
                        System.out.println("1. Browse Menu\n2. Cart Operation\n3. Order Tracking\n4. Submit an Issue\n5. Cancel Order\n6. Exit");
                        int customerChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (customerChoice) {
                            case 1:
                                System.out.println("\nBrowsing Menu:");
                                System.out.println("a. View all items\nb. Search Item\nc. Filter by Category\nd. Sort by Price");
                                String customerChoice1 = scanner.nextLine();

                                if (customerChoice1.equalsIgnoreCase("a")) {
                                    customer.Look_Menu(menu);
                                } else if (customerChoice1.equalsIgnoreCase("b")) {
                                    System.out.print("Name the Menu Item you want to Search: ");
                                    String keyword = scanner.nextLine();
                                    List<MenuItems> Searched = customer.searchItems(menu, keyword);
                                    customer.displaySearchResults(Searched);
                                } else if (customerChoice1.equalsIgnoreCase("c")) {
                                    String keyword1;
                                    while (true) {
                                        System.out.print("Name the category whose items you want to see(type 'quit' for exit): ");
                                        keyword1 = scanner.nextLine();
                                        if (keyword1.equalsIgnoreCase("quit")) {
                                            break;
                                        }
                                        List<MenuItems> Searched1 = customer.filterByCategory(menu, keyword1);
                                        for (MenuItems m : Searched1) {
                                            System.out.println(m);
                                        }
                                    }
                                } else if (customerChoice1.equalsIgnoreCase("d")) {
                                    customer.sortByPrice(menu);
                                } else {
                                    System.out.println("Invalid Entry");
                                }
                                break;

                            case 2:
                                System.out.println("\nCart Operations:");
                                System.out.println("a. Add items\nb. Modify Quantities\nc. Remove item\nd. View Total\ne. Checkout Process");
                                String customerChoice2 = scanner.nextLine();

                                if (customerChoice2.equalsIgnoreCase("a")) {
                                    System.out.print("Name the Item you want to add to the cart: ");
                                    String keyword2 = scanner.nextLine();
                                    System.out.print("Enter the quantity of Item you want to add to the cart: ");
                                    int keyword3 = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    MenuItems MI = findMenuItemByNAME(menu, keyword2);
                                    customer.Add_item_in_Cart(MI, keyword3);
                                } else if (customerChoice2.equalsIgnoreCase("b")) {
                                    System.out.print("Name the Item whose quantity you want to update: ");
                                    String keyword3 = scanner.nextLine();
                                    System.out.print("New quantity: ");
                                    int keyword4 = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    MenuItems MI1 = findMenuItemByNAME(menu, keyword3);
                                    customer.updateCartItemQuantity(MI1, keyword4);
                                } else if (customerChoice2.equalsIgnoreCase("c")) {
                                    System.out.print("Name the Item you want to remove: ");
                                    String keyword13 = scanner.nextLine();
                                    MenuItems MI2 = findMenuItemByNAME(menu, keyword13);
                                    customer.removeItemFromCart(MI2);
                                } else if (customerChoice2.equalsIgnoreCase("d")) {
                                    System.out.println(customer.viewCartTotal());
                                } else if (customerChoice2.equalsIgnoreCase("e")) {
                                    customer.checkout(DataInitializer.Pendings);
                                } else {
                                    System.out.println("Invalid entry");
                                }
                                break;

                            case 3:
                                customer.viewOrderHistory();
                                System.out.print("Enter the specific ID you want to track: ");
                                int key1 = scanner.nextInt();
                                customer.trackOrder(key1);
                                break;

                            case 4:
                                System.out.print("Write down the complaint you want to add: ");
                                String complaint = scanner.nextLine();
                                Issues.add(complaint);
                                break;
                            case 5:
                                customer.cancelOrder(DataInitializer.Pendings);
                                break;
                            case 6:
                                Customer_history.add(customer);
                                for(Customer visited:Customer_history){
                                    System.out.println(visited.getEmail());
                                }
                                System.out.println("Logging out...");
                                customerRunning = false;
                                break;
                            default:
                                System.out.println("Invalid Entry");
                                break;
                        }
                    }
                    break;
                case 3:
                    showHistory(customerHistory);
                    break;
                case 4:
                    FileHandler.writePending(DataInitializer.Pendings);
                    break;
                case 5:
                    System.out.println("Exiting the CANTEEN system...");
                    running = false;

                    FileHandler.writeUserData(customerHistory);
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        scanner.close();
    }

    private static MenuItems findMenuItemByNAME(Menu menu, String fooditem) {
        for (MenuItems M : menu.getItems()) {
            if (M.getName().equalsIgnoreCase(fooditem)) {
                return M;
            }
        }
        return null;
    }
}


