/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This abstract class represents a generic Electronic Device. It 
* provides the base properties (model, age, condition) and defines 
* abstract methods that specific device types must implement.
*/

public abstract class ElectronicDevice implements Tradeable {
    protected int id;
    protected int customerId;
    protected String type;
    protected String model;
    protected int ageInMonths;
    protected Condition condition;
    protected double basePrice;
    protected String status;

    public ElectronicDevice(String model, int ageInMonths, Condition condition, double basePrice) {
        this.model = model;
        this.ageInMonths = ageInMonths;
        this.condition = condition;
        this.basePrice = basePrice;
        this.status = "In Stock";
    }

    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setStatus(String status) { this.status = status; }
    
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public int getAgeInMonths() { return ageInMonths; }
    public String getConditionString() { return condition.toString(); }
    public double getBasePrice() { return basePrice; }
    public String getStatus() { return status; }

    @Override
    public abstract double calculateValue();

    @Override
    public String toString() {
        return String.format("ID: %d | Status: %s | Model: %s | Condition: %s", 
            id, status, model, condition);
    }

    public enum Condition { MINT, GOOD, FAIR, POOR }
}