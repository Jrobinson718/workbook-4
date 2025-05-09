package com.pluralsight;

public class Card {

    private String suit;
    private String value;
    private boolean faceUp;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.faceUp = false;
    }

    public String getSuit() {
        if (faceUp) {
            return this.suit;
        } else {
            return "#";
        }
    }

    public String getValue() {
        if (faceUp) {
            return this.value;
        } else {
            return "#";
        }
    }

    public int getPointValue() {
        if (!faceUp) {
            return 0;
        }else {
            return switch (this.value) {
                case "A" -> 11;
                case "2" -> 2;
                case "3" -> 3;
                case "4" -> 4;
                case "5" -> 5;
                case "6" -> 6;
                case "7" -> 7;
                case "8" -> 8;
                case "9" -> 9;
                case "10", "J", "Q", "K" -> 10;
                default -> 0;
            };
        }
    }

    public boolean faceUp() {
        return this.faceUp;
    }

    public boolean faceDown() {
        return !this.faceUp;
    }

    public boolean flip() {
        this.faceUp = !this.faceUp;
        return this.faceUp;
    }

    @Override
    public String toString() {
        if (faceUp) {
            String suitColor = "";
            if (this.suit.equals("Hearts") || this.suit.equals("Diamonds")){
                suitColor = ColorCodes.RED;
            } else if (this.suit.equals("Spades") || this.suit.equals("Clubs")){
                suitColor = ColorCodes.BLACK;
            }
            return suitColor +  "  - Suit: " + suit + ColorCodes.RESET +
                    "\nValue: " + value;
        }else {
            return "#";
        }
    }
}
