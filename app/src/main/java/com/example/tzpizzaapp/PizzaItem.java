package com.example.tzpizzaapp;

public class PizzaItem {
    private String name;
    private Pizza pizza;
    private int image;
    public PizzaItem(String name, Pizza pizza, int image) {
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
    public double getPrice() {
        return pizza.price();
    }
}
