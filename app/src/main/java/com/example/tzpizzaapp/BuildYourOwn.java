package com.example.tzpizzaapp;

import java.util.ArrayList;

/**
 a build your own pizza subclass of the abstract pizza class
 @author Tyler Amalfa, Zafar Khan
 */
public class BuildYourOwn extends Pizza{
    /**
     creates a build your own pizza object
     @param toppings the toppings on the pizza
     @param sauce the sauce on the pizza
     @param size size of the pizza
     @param extraSauce if the pizza has extra sauce
     @param extraCheese if the pizza has extra cheese
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Sauce sauce, Size size, boolean extraSauce, boolean extraCheese) {
        this.toppings = toppings;
        this.sauce = sauce;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     sets the price of the build your own pizza
     @return returns the price of the pizza as a double
     */
    public double price() {
        double price = 8.99 + 1.49*(toppings.size()-3);
        return super.priceHelper(price);
    }
}
