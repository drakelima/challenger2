package com.challenger;

import java.util.ArrayList;
import java.util.List;

public class Drone {
    private String name;
    private int maxWeight;
    private List<List<Location>> trips;

    public Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.trips = new ArrayList<>();
        this.trips.add(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public boolean canCarry(int weight) {
        return trips.get(trips.size()-1).isEmpty() || trips.get(trips.size()-1).get(0).getTotalWeight() + weight <= maxWeight;
    }

    public int getNumTrips() {
        return trips.size();
    }

    public void addLocation(Location location) {
        trips.get(trips.size()-1).add(location);
        if (trips.get(trips.size()-1).stream().mapToInt(Location::getWeight).sum() > maxWeight) {
            trips.add(new ArrayList<>());
        }
    }

    public List<Location> getTrip(int tripNum) {
        return trips.get(tripNum);
    }
}
