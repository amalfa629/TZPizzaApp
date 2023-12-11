package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a Veggie subclass of the abstract Pizza class
 */
public class Veggie extends Pizza{
    public Veggie(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.BLACK_OLIVE, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM, Topping.TOMATO}));
        sauce = Sauce.VODKA;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     returns the price of the pizza as a double
     @return returns the price
     */
    public double price() {
        double price = 11.99;
        return super.priceHelper(price);
    }
}
