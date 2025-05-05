package com.pluralsight;

public class Reservation {
    //   === Constants ===
    private static final double kingPrice = 139.00;
    private static final double doublePrice = 124.00;
    private static final double weekendSurcharge = .10;
    //   === Instance Variables ===
    private String roomType;
    private int numberOfNights;
    private boolean weekend;

    // Constructor to create a new reservation object.
    // numberOfNights has to be positive.
    // isWeekend true if the stay includes a weekend which adds surcharge, false otherwise
    public Reservation(String roomType, int numberOfNights, boolean isWeekend) {
        if (numberOfNights <= 0){
            throw new IllegalArgumentException("You can't reserve a room for a 0 night stay.");
        }
        this.numberOfNights = numberOfNights;
        this.setRoomType(roomType); // Validates and set's room type using setters logic
        this.weekend = isWeekend;
    }

    //   === Getters ===
    public String getRoomType() {
        return roomType;
    }

    // Calculates and returns the price per night based on room type and if the guests stay includes a weekend
    public double getPrice() {
        double basePricePerNight = 0;

        if ("king".equals(this.roomType)) {
            basePricePerNight = kingPrice;
        }else if ("double".equals(this.roomType)) {
            basePricePerNight = doublePrice;
        }
        if (this.weekend) {
            basePricePerNight *= (1.0 + weekendSurcharge);
        }
        return basePricePerNight;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public boolean isWeekend() {
        return weekend;
    }

    // Calculates the total cost of the reservation
    public double getReservationTotal() {
        return this.getPrice() * this.numberOfNights;
    }

    //   === Setters ===

    // Sets room type for the reservation
    // Only "King" or "Double" are accepted and stores the value in lowercase for later use
    public void setRoomType(String roomType) {
        if (roomType == null){
            throw new IllegalArgumentException("Room type empty or null.");
        }

        String roomTypeLowered = roomType.toLowerCase();

        if ("king".equals(roomTypeLowered) || "double".equals(roomTypeLowered)) {
            this.roomType = roomTypeLowered;
        }else {
            throw new IllegalArgumentException("Invalid room type: '" + roomType + "'. Please input 'King' or 'Double'.");
        }
    }

    // Sets number of nights throwing an exception if numberOfNights is negative.
    public void setNumberOfNights(int numberOfNights) {
        if (numberOfNights <= 0) {
            throw new IllegalArgumentException("Guest must be staying for at least one night.");
        }
        this.numberOfNights = numberOfNights;
    }

    public void setIsWeekend(boolean weekend) {
        this.weekend = weekend;
    }
}
