package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 a supreme subclass of the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class Supreme extends Pizza{
    /**
     creates a supreme pizza object
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public Supreme(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.SAUSAGE, Topping.PEPPERONI, Topping.HAM, Topping.GREEN_PEPPER, Topping.ONION, Topping.BLACK_OLIVE, Topping.MUSHROOM}));
        sauce = Sauce.TOMATO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     calculates the price of the supreme pizza
     @return the price of the pizza as a double
     */
    public double price() {
        double price = 15.99;
        return super.priceHelper(price);
    }
}
