package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a Chicken Parm subclass for the abstract Pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class ChickenParm extends Pizza {
    public ChickenParm(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.FRIED_CHICKEN_CUTLET}));
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
