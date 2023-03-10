package com.challenger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class DeliveryManager {
    private List<Drone> drones;
    private List<Location> locations;
    private List<List<Location>> deliveries;

    public DeliveryManager(List<Drone> drones, List<Location> locations) {
        this.drones = drones;
        this.locations = locations;
        this.deliveries = new ArrayList<>();
        for (int i = 0; i < drones.size(); i++) {
            deliveries.add(new ArrayList<>());
        }
    }

    public void assignDeliveries() {
        for (Drone drone : drones) {
            drone.sortDeliveries();
            List<List<Location>> droneTrips = new ArrayList<>();
            List<Location> currentTrip = new ArrayList<>();
            int currentWeight = 0;
            for (Location location : drone.getDeliveries()) {
                if (currentWeight + location.getWeight() <= drone.getMaxWeight()) {
                    currentTrip.add(location);
                    currentWeight += location.getWeight();
                } else {
                    droneTrips.add(currentTrip);
                    currentTrip = new ArrayList<>();
                    currentTrip.add(location);
                    currentWeight = location.getWeight();
                }
            }
            if (!currentTrip.isEmpty()) {
                droneTrips.add(currentTrip);
            }
        }
    }

    public void generateOutput(String outputFilename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(outputFilename));
        for (int i = 0; i < drones.size(); i++) {
            writer.println("[" + drones.get(i).getName() + "]");

            // Calculate the total number of trips for the current drone
            int totalTrips = deliveries.get(i).size();

            // Print out the deliveries for each trip
            for (int j = 0; j < totalTrips; j++) {
                writer.println("Trip #" + (j + 1));
                List<Location> trip = deliveries.get(i);
                for (int k = 0; k < trip.size(); k++) {
                    writer.print(trip.get(k).getName());
                    if (k < trip.size() - 1) {
                        writer.print(", ");
                    }
                }
                writer.println();
            }

            // If there are no trips, print a message saying so
            if (totalTrips == 0) {
                writer.println("No deliveries assigned to this drone.");
            }

            // Add a blank line to separate the output for different drones
            writer.println();
        }
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Parse input file
        Scanner scanner = new Scanner(new File("input.txt"));
        String[] droneInfo = scanner.nextLine().split(", ");
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < droneInfo.length; i += 2) {
            drones.add(new Drone(droneInfo[i], Integer.parseInt(droneInfo[i + 1])));
        }
        List<Location> locations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] locationInfo = scanner.nextLine().split(", ");
            locations.add(new Location(locationInfo[0], Integer.parseInt(locationInfo[1])));
        }
        scanner.close();

        // Create delivery manager and assign deliveries to drones
        DeliveryManager deliveryManager = new DeliveryManager(drones, locations);
        deliveryManager.assignDeliveries();

        // Generate output file
        deliveryManager.generateOutput("output.txt");
    }
}
