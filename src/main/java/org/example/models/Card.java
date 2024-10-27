package org.example.models;

public class Card {
    private String name;
    private int damage;
    private ElementType element;

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name != null){
            if(name.length() > 3){
                this.name = name;
            }
            throw new IllegalArgumentException("Name is too short");
        }
        throw new IllegalArgumentException("Name must not be empty.");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ElementType getElement() {
        return element;
    }

    public void setElement(ElementType element) {
        this.element = element;
    }
}
