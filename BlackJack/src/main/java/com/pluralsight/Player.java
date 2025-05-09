package com.pluralsight;

public class Player {
    //   === Instance Variables ===
    private String name;
    private Hand hand;
    private boolean bust;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    //   === Getters ===
    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBust() {
        return bust;
    }

    //   === Setter ===
    public void setBust(boolean bust) {
        this.bust = bust;
    }

    public void displayPlayerHand() {
        hand.displayHand(this.name);
    }
}
