package com.pluralsight;

public class Room {
    //   === Instance Variables ===
    private int numberOfBeds;
    private double price;
    private boolean occupied;
    private boolean dirty;

    // Constructor to create a new room object
    // Makes it so that by default a newly created room is not occupied and is clean
    public Room(int numberOfBeds, double price) {
        if (numberOfBeds <= 0) {
            throw new IllegalArgumentException("There must be a bed in the room.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative.");
        }
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.occupied = false;
        this.dirty = false;
    }

    //   === Getters ===
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isDirty() {
        return dirty;
    }

    public boolean isAvailable() {
        return !this.occupied && !this.dirty;
    }

    //   === Methods to change state of room ===
    public void occupy() {
        this.occupied = true;
    }

    private void vacate() {
        this.occupied = false;
        this.dirty = true;
    }

    public void cleanRoom() {
        this.dirty = false;
    }
}
