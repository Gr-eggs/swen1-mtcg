package org.example.models;

import java.util.List;

public class Package {
    private List<Card> cards;
    private static final int CARD_COUNT = 5; // Each package contains 5 cards

    public Package(List<Card> cards) {
        if(cards.size() == CARD_COUNT) {
            this.cards = cards;
        } else {
            throw new IllegalArgumentException("Package must contain exactly 5 cards.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        if(cards.size() == CARD_COUNT) {
            this.cards = cards;
        } else {
            throw new IllegalArgumentException("Package must contain exactly 5 cards.");
        }
    }
}

