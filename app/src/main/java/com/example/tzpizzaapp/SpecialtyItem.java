package com.example.tzpizzaapp;

public class SpecialtyItem {
    private String name;
    private Pizza pizza;
    private int image;
    public SpecialtyItem(String name, Pizza pizza, int image) {
        this.name = name;
        this.pizza = pizza;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public int getImage() {
        return image;
    }
}
