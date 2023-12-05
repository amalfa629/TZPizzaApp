package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a seafood subclass of the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class Seafood extends Pizza{
    /**
     creates a seafood pizza object
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public Seafood(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.SHRIMP, Topping.SQUID, Topping.CRAB_MEAT}));
        sauce = Sauce.ALFREDO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     determines the price for a seafood pizza
     @return returns a double with the price
     */
    public double price() {
        double price = 17.99;
        return super.priceHelper(price);
    }
}
