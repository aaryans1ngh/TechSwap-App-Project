/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Final Submission
*
* This class serves as the main driver for the application. It 
* instantiates objects and runs a simulation to demonstrate the 
* functionality of the classes.
*/

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final String dbName = "TechSwap.db";
        Connection conn = SQLiteDatabase.connect(dbName);
        Scanner scan = new Scanner(System.in);

        if (conn != null) {
            TechSwapDb.createTables(conn);
            Logger.log("Application started.");
        }

        boolean running = true;
        System.out.println("Welcome to the TechSwap Inventory Management System");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Register New Customer");
            System.out.println("2. Create Trade-In Quote (Add Item)");
            System.out.println("3. View Current Inventory");
            System.out.println("4. Update Item Status (e.g. Sold)");
            System.out.println("5. Remove Item from Database");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    handleNewCustomer(conn, scan);
                    break;
                case "2":
                    handleNewQuote(conn, scan);
                    break;
                case "3":
                    handleViewInventory(conn);
                    break;
                case "4":
                    handleUpdateStatus(conn, scan);
                    break;
                case "5":
                    handleDeleteItem(conn, scan);
                    break;
                case "6":
                    running = false;
                    Logger.log("Application closed.");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }


    private static void handleNewCustomer(Connection conn, Scanner scan) {
        System.out.print("Enter Customer Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scan.nextLine();

        Customer c = new Customer(name, phone);
        TechSwapDb.addCustomer(conn, c);
        System.out.println("Customer saved!");
        Logger.log("New Customer Added: " + name);
    }

    private static void handleNewQuote(Connection conn, Scanner scan) {
        System.out.print("Enter Customer Name for this quote: ");
        String custName = scan.nextLine();
        Customer cust = TechSwapDb.getCustomer(conn, custName);

        if (cust == null) {
            System.out.println("Customer not found. Please register them first.");
            return;
        }

        System.out.println("Device Type? (1) Smartphone (2) Laptop");
        String typeChoice = scan.nextLine();

        System.out.print("Model Name: ");
        String model = scan.nextLine();
        System.out.print("Age (months): ");
        int age = Integer.parseInt(scan.nextLine());
        System.out.print("Base Retail Price: ");
        double price = Double.parseDouble(scan.nextLine());
        
        System.out.println("Condition: (1) MINT (2) GOOD (3) FAIR (4) POOR");
        String condChoice = scan.nextLine();
        ElectronicDevice.Condition cond = ElectronicDevice.Condition.GOOD; // Default
        if (condChoice.equals("1")) cond = ElectronicDevice.Condition.MINT;
        if (condChoice.equals("3")) cond = ElectronicDevice.Condition.FAIR;
        if (condChoice.equals("4")) cond = ElectronicDevice.Condition.POOR;

        ElectronicDevice device = null;

        if (typeChoice.equals("1")) {
            System.out.print("Storage Capacity (GB): ");
            int storage = Integer.parseInt(scan.nextLine());
            device = new Smartphone(model, age, cond, price, storage);
        } else {
            System.out.print("RAM Size (GB): ");
            int ram = Integer.parseInt(scan.nextLine());
            device = new Laptop(model, age, cond, price, ram);
        }

        TechSwapDb.addDevice(conn, device, cust.getId());
        
        System.out.println("\n>> QUOTE GENERATED <<");
        System.out.println(device.toString());
        System.out.printf("Offered Trade-In Value: $%.2f%n", device.calculateValue());
        Logger.log("Quote generated for " + custName + " - " + model);
    }

    private static void handleViewInventory(Connection conn) {
        ArrayList<ElectronicDevice> inventory = TechSwapDb.getAllInventory(conn);
        System.out.println("\n--- CURRENT INVENTORY ---");
        if (inventory.isEmpty()) {
            System.out.println("No items in stock.");
        } else {
            for (ElectronicDevice d : inventory) {
                System.out.println(d.toString());
            }
        }
    }

    private static void handleUpdateStatus(Connection conn, Scanner scan) {
        System.out.print("Enter Device ID to update: ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.print("Enter new status (e.g. Sold, Recycled): ");
        String status = scan.nextLine();
        
        TechSwapDb.updateDeviceStatus(conn, id, status);
        Logger.log("Device " + id + " updated to " + status);
    }

    private static void handleDeleteItem(Connection conn, Scanner scan) {
        System.out.print("Enter Device ID to delete: ");
        int id = Integer.parseInt(scan.nextLine());
        
        TechSwapDb.deleteDevice(conn, id);
        Logger.log("Device " + id + " deleted.");
    }
}