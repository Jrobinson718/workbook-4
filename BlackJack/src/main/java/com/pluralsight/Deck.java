package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();

        String[] suits = {"Hearts","Spades","Diamonds","Clubs"};
        String[] values = {"2","3","4","5","6","7","8",
                "9","10","J","Q","K","A"};

        for (String suit : suits) {
            for (String value : values){
                Card c = new Card(suit,value);
                cards.add(c);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.size() > 0) {
            Card c = cards.remove(0);
            return c;
        }else {
            return null;
        }
    }

    public int cardsLeft() {
        return this.cards.size();
    }
}
