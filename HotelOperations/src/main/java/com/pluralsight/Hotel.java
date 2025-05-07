package com.pluralsight;

public class Hotel {

    //   === Instance Variables ===
    private String name;
    private int numberOfSuites;
    private int numberOfBasicRooms;
    private int bookedSuites;
    private int bookedBasicRooms;

    //   === Constructors ===

    // Constructor to create a hotel object
    // Holds number of suites and number of basic rooms while booked rooms default at 0
    public Hotel(String name, int numberOfSuites, int numberOfRooms) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name can't be null or blank.");
        }
        if (numberOfSuites < 0 || numberOfRooms < 0) {
            throw new IllegalArgumentException("Number of suites and basic rooms can't be negative.");
        }

        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfBasicRooms = numberOfRooms;
        this.bookedSuites = 0;
        this.bookedBasicRooms = 0;
    }

    // Constructor to create a hotel object
    // Holds total number of suites, basic rooms the initial number of booked suites and basic rooms
    public Hotel(String name, int numberOfSuites, int numberOfRooms, int bookedSuites, int bookedBasicRooms) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name can't be null or empty.");
        }
        if (numberOfSuites < 0 || numberOfRooms < 0 || bookedSuites < 0 || bookedBasicRooms < 0) {
            throw new IllegalArgumentException("Room count and booked room count can't be negative.");
        }
        if (bookedSuites > numberOfSuites) {
            throw new IllegalArgumentException("Booked suites (" + bookedSuites + ") can't exceed total basic rooms (" +
                    numberOfSuites + ").");
        }
        if (bookedBasicRooms > numberOfBasicRooms) {
            throw new IllegalArgumentException("Booked basic rooms (" + bookedBasicRooms + ") can't exceed total basic rooms (" +
                    numberOfBasicRooms + ").");
        }

        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfBasicRooms = numberOfRooms;
        this.bookedSuites = bookedSuites;
        this.bookedBasicRooms = bookedBasicRooms;
    }

    //   === Getters for Hotel info ===

    public String getName() {
        return name;
    }

    public int getNumberOfSuites() {
        return numberOfSuites;
    }

    public int getNumberOfBasicRooms() {
        return numberOfBasicRooms;
    }

    public int getBookedSuites() {
        return bookedSuites;
    }

    public int getBookedBasicRooms() {
        return bookedBasicRooms;
    }

    public int getAvailableSuites() {
        return this.numberOfSuites - this.bookedSuites;
    }

    public int getAvailableRooms() {
        return this.numberOfBasicRooms - this.bookedBasicRooms;
    }

    //   === Methods ===

    // Method to book a number of rooms of a specific type (suite/basic)
    // Updates booked count if rooms are available
    public boolean bookRoom(int numberOfRoomsToBook, boolean isSuite) {
        if (numberOfRoomsToBook <= 0) {
            System.out.println("Booking failed: Number of rooms can't be negative to book. Requested: " + numberOfRoomsToBook);
            return false;
        }

        if (isSuite) {
            if (getAvailableSuites() >= numberOfRoomsToBook) {
                this.bookedSuites += numberOfRoomsToBook;
                System.out.println("Booking successful! " + numberOfRoomsToBook + " suite(s) at " + this.name + ".");
                return true;
            }else {
                System.out.println("Booking failed: Not enough suites available at " + this.name + ".\n" +
                        "Requested: " + numberOfRoomsToBook + ", Available: " + getAvailableSuites());
                return false;
            }
        }else {
            if (getAvailableRooms() >= numberOfRoomsToBook) {
                this.bookedBasicRooms += numberOfRoomsToBook;
                System.out.println("Booking successful! " + numberOfRoomsToBook + " basic room(s) at " + this.name + ".");
                return true;
            }else {
                System.out.println("Booking failed: Not enough basic rooms available at " + this.name + ".\n" +
                        "Requested: " + numberOfRoomsToBook + ", Available: " + getAvailableRooms());
                return false;
            }
        }
    }
}
