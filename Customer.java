/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This class represents a Customer. It is a simple data object 
* used to store and retrieve the contact information of the 
* person trading in items.
*/

public class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, phoneNumber);
    }
}