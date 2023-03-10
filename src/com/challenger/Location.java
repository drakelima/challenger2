package com.challenger;

public class Location {
    private String name;
    private int weight;

    public Location(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
