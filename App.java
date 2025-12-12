/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This class serves as the main driver for the application. It 
* instantiates objects and runs a simulation to demonstrate the 
* functionality of the classes.
*/

import java.sql.Connection;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        final String dbName = "TechSwap.db";
        System.out.println("Aaryan Singh - Week 4 Tech Swap DB Implementation\n");
        Connection conn = SQLiteDatabase.connect(dbName);

        if (conn != null) {
            // 1. Create Tables
            if (TechSwapDb.createTables(conn)) {
                
                // 2. Create and Add a Customer
                Customer newCust = new Customer("Aaryan Singh", "704-555-0199");
                TechSwapDb.addCustomer(conn, newCust);
                Customer dbCust = TechSwapDb.getCustomer(conn, "Aaryan Singh");
                System.out.println("\nCustomer Added: " + dbCust);

                // 3. Create Devices
                Smartphone phone = new Smartphone("iPhone 13", 12, ElectronicDevice.Condition.GOOD, 800.00, 128);
                Laptop laptop = new Laptop("MacBook Pro", 24, ElectronicDevice.Condition.MINT, 1500.00, 16);

                // 4. Add Devices to Inventory (Linked to Customer ID)
                if (dbCust != null) {
                    System.out.println("Adding devices to database...");
                    TechSwapDb.addDevice(conn, phone, dbCust.getId());
                    TechSwapDb.addDevice(conn, laptop, dbCust.getId());
                }

                // 5. Read: Get All Inventory
                System.out.println("\n--- Current Inventory ---");
                ArrayList<ElectronicDevice> inventory = TechSwapDb.getAllInventory(conn);
                for (ElectronicDevice d : inventory) {
                    System.out.println(d);
                }

                // 6. Update: Set the Laptop to "Sold"
                if (inventory.size() >= 2) {
                    int laptopId = inventory.get(1).getId(); 
                    TechSwapDb.updateDeviceStatus(conn, laptopId, "Sold");
                }

                // 7. Delete: Remove the Smartphone
                if (inventory.size() >= 1) {
                    int phoneId = inventory.get(0).getId();
                    TechSwapDb.deleteDevice(conn, phoneId);
                }

                // 8. Read: Final Inventory Check
                System.out.println("\n--- Final Inventory (After Sale & Delete) ---");
                inventory = TechSwapDb.getAllInventory(conn);
                for (ElectronicDevice d : inventory) {
                    System.out.println(d);
                }
            }
        }
    }
}