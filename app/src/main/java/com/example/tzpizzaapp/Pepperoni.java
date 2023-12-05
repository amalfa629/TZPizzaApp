package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a pepperoni subclass for the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class Pepperoni extends Pizza{
    /**
     creates a pepperoni pizza object
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.PEPPERONI}));
        sauce = Sauce.TOMATO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     sets the price of the pepperoni pizza
     @return returns the price as a double
     */
    public double price() {
        double price = 10.99;
        return super.priceHelper(price);
    }
}