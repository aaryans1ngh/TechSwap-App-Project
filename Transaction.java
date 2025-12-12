/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This class represents a Transaction. It demonstrates composition 
* by containing a Customer and a list of ElectronicDevices, and 
* calculates the total payout including bulk bonuses.
*/

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private Customer customer;
    private ArrayList<ElectronicDevice> items;
    private Date date;

    public Transaction(int transactionId, Customer customer) {
        this.transactionId = transactionId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.date = new Date();
    }

    // Method to add items to the transaction
    public void addItem(ElectronicDevice device) {
        items.add(device);
    }

    public double getGrandTotal() {
        double total = 0;
        for (ElectronicDevice device : items) {
            total += device.calculateValue();
        }

        // Bulk Bonus Logic
        if (items.size() > 2) {
            System.out.println(">> Bulk Trade-In Bonus Applied! (+10%)");
            total = total * 1.10;
        }

        return total;
    }

    // Prints the receipt to the terminal
    public void printReceipt() {
        System.out.println("\n--- TRADE-IN RECEIPT ---");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + date); 
        System.out.println("Customer: " + customer.toString());
        System.out.println("--------------------------------------------------");

        for (ElectronicDevice device : items) {
            String lineItem = String.format("%-50s $%.2f", 
                device.toString(), device.calculateValue());
            System.out.println(lineItem);
        }

        System.out.println("--------------------------------------------------");
        System.out.println(String.format("GRAND TOTAL PAYOUT: $%.2f", getGrandTotal()));
        System.out.println("==================================================\n");
    }
}