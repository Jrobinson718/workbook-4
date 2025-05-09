package com.pluralsight;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void deal(Card card) {
        if (!card.faceUp()) {
            card.flip();
        }
        this.cards.add(card);
    }

    public int getSize() {
        return this.cards.size();
    }

    public int getValue() {
        int total = 0;
        int aceCount = 0;
        for (Card c : this.cards) {
            int cardValue = c.getPointValue();
            if (c.getValue().equals("A") && c.faceUp()) {
                aceCount++;
            }
            total += cardValue;
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public void displayHand(String ownerName) {
        System.out.println(ownerName + "'s Hand:\n");
        if (cards.isEmpty()) {
            System.out.println("[Empty]");
            return;
        }
        for (Card card : cards) {
            System.out.println(card.toString());
        }
        System.out.println("Total value: " + getValue() + "\n");
    }
}
