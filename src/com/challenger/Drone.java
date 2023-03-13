package com.challenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Drone {
    private String name;
    private int capacity;
    private int remainingCapacity;
    private List<Location> deliveries;

    public Drone(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.deliveries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public List<Location> getDeliveries() {
        return deliveries;
    }

    public void addDelivery(Location location) {
        deliveries.add(location);
        remainingCapacity -= location.getWeight();
    }

    public void unload() {
        deliveries.clear();
        remainingCapacity = capacity;
    }

    public boolean canCarry(int weight) {
        return weight <= remainingCapacity;
    }

    public void reset() {
        unload();
        remainingCapacity = capacity;
    }

    public void sortDeliveries() {
        Collections.sort(deliveries, new Comparator<Location>() {
            @Override
            public int compare(Location o1, Location o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });
    }
}
