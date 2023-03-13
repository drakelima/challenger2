package com.challenger;

import java.util.*;

public class DroneDeliveryService {
    static List<Drone> parseDrones(String line) {
        List<String> droneStrings = Arrays.asList(line.split(", "));
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < droneStrings.size(); i += 2) {
            String name = droneStrings.get(i).substring(1); // remove '['
            String capacityString = droneStrings.get(i + 1).replaceAll("[\\[\\]\\s]+", "");
            int maxWeight = Integer.parseInt(capacityString);
            drones.add(new Drone(name, maxWeight));
        }
        return drones;
    }


    static List<Location> parseLocations(Scanner scanner) {
        List<Location> locations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String name = line.substring(1, line.indexOf("]"));
            String weightString = line.substring(line.lastIndexOf("[") + 1, line.lastIndexOf("]"));
            System.out.println("weightString: " + weightString);
            int weight = Integer.parseInt(weightString);
            locations.add(new Location(name, weight));
        }
        // Sort locations by weight (descending order)
        locations.sort(Comparator.comparing(Location::getWeight).reversed());
        return locations;
    }
}

