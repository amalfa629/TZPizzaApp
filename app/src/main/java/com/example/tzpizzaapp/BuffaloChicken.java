package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a Buffalo Chicken subclass of the abstract Pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class BuffaloChicken extends Pizza {
    public BuffaloChicken(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.FRIED_CHICKEN_CUTLET, Topping.BLUE_CHEESE_CRUMBLES}));
        sauce = Sauce.BUFFALO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     returns the price of the pizza as a double
     @return returns the price
     */
    public double price() {
        double price = 10.99;
        return super.priceHelper(price);
    }
}
