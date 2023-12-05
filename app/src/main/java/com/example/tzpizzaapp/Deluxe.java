package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a deluxe subclass of the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class Deluxe extends Pizza{
    /**
     creates a deluxe pizza object
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public Deluxe(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM}));
        sauce = Sauce.TOMATO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     sets the price of the deluxe pizza
     @return returns the price as a double
     */
    public double price() {
        double price = 14.99;
        return super.priceHelper(price);
    }
}
