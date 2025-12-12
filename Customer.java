/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This class represents a Customer. It is a simple data object 
* used to store and retrieve the contact information of the 
* person trading in items.
*/

public class Customer {
    private int id;
    private String name;
    private String phoneNumber;
    private int totalSales;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalSales = 0;
    }

    public Customer(int id, String name, String phoneNumber, int totalSales) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalSales = totalSales;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getTotalSales() { return totalSales; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, name, phoneNumber);
    }
}