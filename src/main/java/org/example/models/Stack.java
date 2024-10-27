package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<Card> cards;

    public Stack() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public int getCardCount() {
        return cards.size();
    }
}

