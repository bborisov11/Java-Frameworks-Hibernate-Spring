package Problem01_Vehicles.vehicles;

/**
 * Created by Bludya on 3.7.2016 г..
 * All rights reserved!
 */
public class Truck extends Vehicle {
    private static final double addedConsumption = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + addedConsumption);
    }

    @Override
    public void refuel(double amount){
        super.refuel(amount*0.95);
    }

}
