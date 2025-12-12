/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This class represents a Smartphone. It extends ElectronicDevice 
* to include storage capacity and implements specific valuation 
* logic based on phone market depreciation.
*/

public class Smartphone extends ElectronicDevice {
    private int storageCapacityGB;

    public Smartphone(String model, int ageInMonths, Condition condition, double basePrice, int storageCapacityGB) {
        super(model, ageInMonths, condition, basePrice);
        this.storageCapacityGB = storageCapacityGB;
    }

    @Override
    public double calculateValue() {
        double value = basePrice;

        // Depreciation: Lose $10 per month of age
        value -= (ageInMonths * 10);

        // Condition Logic
        switch (condition) {
            case MINT -> value *= 1.0; // No change
            case GOOD -> value *= 0.8; // 20% drop
            case FAIR -> value *= 0.5; // 50% drop
            case POOR -> value *= 0.1; // 90% drop
        }

        // Feature Bonus: Add $0.50 for every GB of storage
        value += (storageCapacityGB * 0.50);

        // Ensure value doesn't go below $0
        return Math.max(value, 0);
    }

    @Override
    public String toString() {
        return String.format("%s | Storage: %dGB [Smartphone]", 
            super.toString(), storageCapacityGB);
    }
}