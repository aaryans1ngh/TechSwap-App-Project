/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This class serves as the main driver for the application. It 
* instantiates objects and runs a simulation to demonstrate the 
* functionality of the classes.
*/

public class App {
    public static void main(String[] args) {
        System.out.println("Thank You for Visiting Tech Swap!");

        // 1. Create a Customer
        Customer myCustomer = new Customer("Aaryan Singh", "704-644-9445");

        // 2. Start a Transaction
        Transaction trans1 = new Transaction(101, myCustomer);

        // 3. Create Devices (demonstrating Polymorphism)
        // Smartphone: 2 years old, Good condition, 64GB storage
        Smartphone phone = new Smartphone("iPhone 12", 24, ElectronicDevice.Condition.GOOD, 800.00, 64);
        
        // Laptop: 1 year old, Mint condition, 16GB RAM
        Laptop laptop = new Laptop("Dell XPS", 12, ElectronicDevice.Condition.MINT, 1200.00, 16);
        
        // Old Phone: 4 years old, Poor condition (Cracked screen)
        Smartphone oldPhone = new Smartphone("Galaxy S8", 48, ElectronicDevice.Condition.POOR, 600.00, 32);

        // 4. Add items to transaction
        trans1.addItem(phone);
        trans1.addItem(laptop);
        trans1.addItem(oldPhone); // This 3rd item triggers the Bulk Bonus

        // 5. Output the result
        trans1.printReceipt();
    }
}