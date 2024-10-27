package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private static final int MAX_DECK_SIZE = 4;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        if(cards.size() <= MAX_DECK_SIZE) {
            this.cards = cards;
        } else {
            throw new IllegalArgumentException("Deck cannot contain more than 4 cards.");
        }
    }

    public void addCard(Card card) {
        if(this.cards.size() < MAX_DECK_SIZE) {
            this.cards.add(card);
        } else {
            throw new IllegalStateException("Deck cannot contain more than 4 cards.");
        }
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public int getDeckSize() {
        return cards.size();
    }
}

