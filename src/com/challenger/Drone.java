package com.challenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Drone {
    private String name;
    private int maxWeight;
    private List<Location> deliveries;

    public Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.deliveries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Location> getDeliveries() {
        return deliveries;
    }

    public void addDelivery(Location location) {
        deliveries.add(location);
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
