package Problem01_Vehicles.vehicles;

/**
 * Created by Bludya on 3.7.2016 г..
 * All rights reserved!
 */
public class Car extends Vehicle{
    private final static double addedConsumption = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + addedConsumption);
    }
}
