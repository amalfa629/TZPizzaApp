package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a meatzza subclass of the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class Meatzza extends Pizza{
    /**
     creates a meatzza pizza object
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public Meatzza(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM}));
        sauce = Sauce.TOMATO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     sets the price of the meatzza
     @return returns the price as a double
     */
    public double price() {
        double price = 16.99;
        return super.priceHelper(price);
    }
}
