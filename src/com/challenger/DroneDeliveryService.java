package com.challenger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DroneDeliveryService {
    public static void main(String[] args) throws FileNotFoundException {
        // read input file
        Scanner scanner = new Scanner(new File("input2.txt"));
        String droneLine = scanner.nextLine();
        String[] droneTokens = droneLine.split(", ");
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < droneTokens.length; i += 2) {
            drones.add(new Drone(droneTokens[i], Integer.parseInt(droneTokens[i+1])));
        }
        List<Location> locations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String locationLine = scanner.nextLine();
            String[] locationTokens = locationLine.split(", ");
            locations.add(new Location(locationTokens[0], Integer.parseInt(locationTokens[1])));
        }
        scanner.close();

        // sort locations by weight in descending order
        locations.sort(Comparator.comparingInt(Location::getWeight).reversed());

        // assign locations to drones
        for (Location location : locations) {
            Drone availableDrone = null;
            for (Drone drone : drones) {
                if (drone.canCarry(location.getWeight()) && (availableDrone == null || availableDrone.getNumTrips() > drone.getNumTrips())) {
                    availableDrone = drone;
                }
            }
            if (availableDrone != null) {
                availableDrone.addLocation(location);
            } else {
                System.out.println("No drone available for location " + location.getName());
            }
        }

        // print results
        for (Drone drone : drones) {
            System.out.println("[" + drone.getName() + "]");
            for (int i = 0; i < drone.getNumTrips(); i++) {
                System.out.println("Trip #" + (i+1));
                List<Location> trip = drone.getTrip(i);
                for (int j = 0; j < trip.size(); j++) {
                    System.out.print(trip.get(j).getName());
                    if (j < trip.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }
}
