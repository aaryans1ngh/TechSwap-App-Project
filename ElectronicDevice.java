/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This abstract class represents a generic Electronic Device. It 
* provides the base properties (model, age, condition) and defines 
* abstract methods that specific device types must implement.
*/

public abstract class ElectronicDevice implements Tradeable {
    protected String model;
    protected int ageInMonths;
    protected Condition condition;
    protected double basePrice;

    public ElectronicDevice(String model, int ageInMonths, Condition condition, double basePrice) {
        this.model = model;
        this.ageInMonths = ageInMonths;
        this.condition = condition;
        this.basePrice = basePrice;
    }

    @Override
    public abstract double calculateValue();

    @Override
    public String toString() {
        return String.format("Model: %s | Condition: %s | Age: %d months", 
            model, condition, ageInMonths);
    }

    public enum Condition {
        MINT, GOOD, FAIR, POOR
    }
}