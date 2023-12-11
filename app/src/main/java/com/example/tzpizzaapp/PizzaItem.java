package com.example.tzpizzaapp;

/**
 PizzaItem class for the pizza maker
 @author Tyler Amalfa, Zafar Khan
 */
public class PizzaItem {
    private String name;
    private Pizza pizza;
    private int image;

    /**
     constructor for the pizza item
     @param name name of the pizza
     @param pizza type of pizza
     @param image image for the type of pizza
     */
    public PizzaItem(String name, Pizza pizza, int image) {
        this.name = name;
        this.pizza = pizza;
        this.image = image;
    }

    /**
     returns the name as a String
     @return name
     */
    public String getName() {
        return name;
    }

    /**
     returns the pizza as a Pizza object
     @return pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     returns the image as an int
     @return image
     */
    public int getImage() {
        return image;
    }

    /**
     returns the price of the pizza as a double
     @return price
     */
    public double getPrice() {
        return pizza.price();
    }
}
