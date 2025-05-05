package com.pluralsight;

public class Main {
    public static void main(String[] args) {

        //   === Tests ===
        try {
            Room room101 = new Room(1, 99.00);
            System.out.println(room101);

            room101.checkIn();
            System.out.println(room101);

            room101.checkIn();

            room101.cleanRoom();

            room101.checkOut();
            System.out.println(room101);

            room101.checkIn();

            room101.cleanRoom();
            System.out.println(room101);

            room101.checkIn();
            System.out.println(room101);

        }catch (IllegalArgumentException e) {
            System.out.println("Error creating room: " + e.getMessage());
        }

        try {
            Employee employee1 = new Employee(1024235, "Alisson", "Sales", 20.0 );
            System.out.println(employee1);

            employee1.punchTimeCard(9.0);
            System.out.println(employee1.punchedIn());

            employee1.punchTimeCard(12.5);
            System.out.println(employee1.punchedIn());
            System.out.println("Hours worked so far: " + employee1.getHoursWorked() + "\n");

            employee1.punchTimeCard(13.0);

            employee1.punchTimeCard(17.0);
            System.out.println("Hours worked so far: " + employee1.getHoursWorked() + "\n");

            System.out.println(employee1);

            try {
                employee1.punchTimeCard(14.0);
                employee1.punchTimeCard(13.0);
            }catch (IllegalArgumentException e){
                System.out.println("Caught error: " + e.getMessage());
            }
        }catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error during employee operations: " + e.getMessage());
        }

        try {
            Reservation res1 = new Reservation("King", 3, true);
            System.out.println(res1);
           } catch (IllegalArgumentException e) {
            System.out.println("Error creating reservation 1: " + e.getMessage());
        }

        try {
            Reservation res2 = new Reservation("double", 5, false);
            System.out.println(res2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating reservation 2: " + e.getMessage());
        }

        try {
            Reservation res3 = new Reservation("Double", 2, false); // Start with a valid one
            System.out.println("Initial state:");
            System.out.println(res3);

            res3.setNumberOfNights(4);
            res3.setIsWeekend(true);
            res3.setRoomType("KING");

            System.out.println(res3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during reservation modification: " + e.getMessage());
        }

        try {
            Reservation res4 = new Reservation("king", 0, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            Reservation res5 = new Reservation("suite", 2, true);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            Reservation res6 = new Reservation("double", 1, false); // Valid start
            res6.setNumberOfNights(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            Reservation res7 = new Reservation("king", 1, false); // Valid start
            res7.setRoomType("twin");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}