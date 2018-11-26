package Problem01_Vehicles;

import Problem01_Vehicles.vehicles.Car;
import Problem01_Vehicles.vehicles.Truck;
import Problem01_Vehicles.vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Bludya on 3.7.2016 г..
 * All rights reserved!
 */
public class Main {
    static Vehicle car;
    static Vehicle truck;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 2; i++) {
            String[] info = reader.readLine().split("\\s+");

            String vehicle = info [0];
            double fuelQuantity = Double.parseDouble(info[1]);
            double fuelConsumption = Double.parseDouble(info[2]);
            switch(vehicle){
                case "Car":
                    car = new Car(fuelQuantity, fuelConsumption);
                    break;
                case "Truck":
                    truck = new Truck(fuelQuantity, fuelConsumption);
                    break;
            }
        }

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] info = reader.readLine().split("\\s+");

            String vehicle = info[1];
            double amount = Double.parseDouble(info[2]);
            NumberFormat format = DecimalFormat.getInstance();
            format.setMinimumFractionDigits(0);

            switch (info[0]){
                case "Drive":
                    boolean driven = tryDriveVehicle(vehicle, amount);
                    String message = driven ?
                            vehicle + " travelled " + format.format(amount) + " km" :
                            vehicle + " needs refueling";
                    System.out.println(message);
                    break;
                case "Refuel":
                    tryRefuelVehicle(vehicle, amount);
                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
    }

    private static void tryRefuelVehicle(String vehicle, double amount) {
        switch(vehicle){
            case "Car":
                car.refuel(amount);
                break;
            case "Truck":
                truck.refuel(amount);
                break;
        }
    }

    private static boolean tryDriveVehicle(String vehicle, double amount) {
        switch(vehicle){
            case "Car":
                return car.drive(amount);
            case "Truck":
                return truck.drive(amount);
            default:
                return false;
        }
    }
}
