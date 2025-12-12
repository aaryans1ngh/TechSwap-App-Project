/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This interface defines the contract for any item that can be 
* traded into the store. It ensures that all tradeable items have 
* a method to calculate their monetary value.
*/

public interface Tradeable {
    // Calculates the trade-in value of the item
    double calculateValue();
}