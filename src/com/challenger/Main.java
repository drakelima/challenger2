package com.challenger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.challenger.DroneDeliveryService.parseDrones;
import static com.challenger.DroneDeliveryService.parseLocations;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Read input from file
        Scanner scanner = new Scanner(new File("input.txt"));
        List<Drone> drones = parseDrones(scanner.nextLine());
        List<Location> locations = parseLocations(scanner);
        scanner.close();

        System.out.println("Drones:");
        drones.forEach(System.out::println);
        System.out.println("Locations:");
        locations.forEach(System.out::println);

        // Calculate deliveries for each drone
        for (Drone drone : drones) {
            List<Location> remainingLocations = new ArrayList<>(locations);
            List<List<Location>> trips = new ArrayList<>();
            while (!remainingLocations.isEmpty()) {
                List<Location> currentTrip = new ArrayList<>();
                int remainingCapacity = drone.getCapacity();
                for (Location location : remainingLocations) {
                    if (location.getWeight() <= remainingCapacity) {
                        currentTrip.add(location);
                        remainingCapacity -= location.getWeight();
                    }
                }
                trips.add(currentTrip);
                remainingLocations.removeAll(currentTrip);
            }
            // Print deliveries for this drone
            System.out.println("[" + drone.getName() + "]");
            for (int i = 0; i < trips.size(); i++) {
                System.out.println("Trip #" + (i + 1));
                String locationsString = trips.get(i).stream()
                        .map(Location::getName)
                        .collect(Collectors.joining(", "));
                System.out.println(locationsString);
            }
            System.out.println();
        }
    }
}
