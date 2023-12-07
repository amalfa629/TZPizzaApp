package com.example.tzpizzaapp;

public class SpecialtyItem {
    private Pizza pizza;
    private int image;
    public SpecialtyItem(Pizza pizza, int image) {
        this.pizza = pizza;
        this.image = image;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public int getImage() {
        return image;
    }
}
