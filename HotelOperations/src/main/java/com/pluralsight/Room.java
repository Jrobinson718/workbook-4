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

    public boolean occupied() {
        return occupied;
    }

    public boolean dirty() {
        return dirty;
    }

    public boolean isAvailable() {
        return !this.occupied && !this.dirty;
    }

    //   === Methods to change state of room ===

    // Checks guest into a room
    // The room must be available and not dirty to check into it
    public void checkIn() {
        if (isAvailable()) {
            this.occupied = true;
            this.dirty = true;
            System.out.println("\nCheck-in Successful! Room is occupied and marked as dirty.");
        }else {
            System.out.println("\nCheck-in failed: Room is not available.");
            if (occupied) {
                System.out.println("Reason: Room is already occupied.\n");
            }
            if (dirty) {
                System.out.println("Reason: Room is dirty and needs to be cleaned.\n");
            }
        }
    }

    // Checks guest out of room
    // The room must be occupied to be checked out of
    // After check-out, the room becomes unoccupied but is marked/remains dirty
    public void checkOut() {
        if (this.occupied) {
            this.occupied = false;
            this.dirty = true;

            System.out.println("\nCheck-out Successful! Room is unoccupied and marked as dirty.");
        }else {
            System.out.println("\nCheck-out failed: Room is not occupied.");
        }
    }

    // Cleans the room
    // Must be unoccupied to be cleaned
    public void cleanRoom() {
        if (this.occupied) {
            System.out.println("Cannot clean an occupied room.");
        } else if (!this.dirty) {
            System.out.println("\nRoom is already clean.");
        }else {
            this.dirty = false;
            System.out.println("\nRoom has been cleaned. It is now available for check-in!");
        }
    }

    @Override
    public String toString() {
        return "Room status:\n" +
                "Beds: " + numberOfBeds +
                "\nPrice: $" + String.format("%.2f", price) +
                "\nOccupied: " + occupied +
                "\nDirty: " + dirty +
                "\nAvailable: " + isAvailable();
    }
}
