/*******************************************************************
* Name: Aaryan Singh
* Date: November 29, 2025
* Assignment: SDC330 Course Project - Week 3
*
* This class represents a Laptop. It extends ElectronicDevice to 
* include RAM specifications and implements valuation logic that 
* prioritizes memory size and condition.
*/

public class Laptop extends ElectronicDevice {
    private int ramSizeGB;

    public Laptop(String model, int ageInMonths, Condition condition, double basePrice, int ramSizeGB) {
        super(model, ageInMonths, condition, basePrice);
        this.ramSizeGB = ramSizeGB;
    }

    @Override
    public double calculateValue() {
        double value = basePrice;
        
        // Depreciation
        value -= (ageInMonths * 5);

        // RAM Bonus
        if (ramSizeGB >= 16) {
            value += 100;
        } else if (ramSizeGB >= 8) {
            value += 50;
        }

        if (condition == Condition.POOR) {
            value = value / 2;
        }

        return Math.max(value, 0);
    }

    @Override
    public String toString() {
        return String.format("%s | RAM: %dGB [Laptop]", 
            super.toString(), ramSizeGB);
    }
}