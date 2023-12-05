package com.example.tzpizzaapp;

import java.util.ArrayList;

/**
 Creates an abstract Pizza type with properties that all subclasses inherit
 @author Tyler Amalfa, Zafar Khan
 */

public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     returns the price of the pizza based on the type of pizza and the size
     @return a type Double with the price of the pizza
     */
    public abstract double price();

    /**
     Helps calculate the price of the pizza based on the size
     @param price the base price of the type of pizza
     @return the price based on the type of pizza and the size of the pizza
     */
    protected double priceHelper(double price) {
        if(size.equals(Size.MEDIUM)) price += 2;
        else if(size.equals(Size.LARGE)) price += 4;
        return price;
    }

    /**
     gets the toppings
     @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     gets the size of the pizza
     @return returns the size of the pizza as type Size
     */
    public Size getSize() {
        return size;
    }

    /**
     gets the sauce
     @return returns the sauce as type sauce
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     returns true or false if depending on whether the pizza has extra sauce
     @return true or false
     */
    public boolean isExtraSauce() {
        return extraSauce;
    }

    /**
     returns true or false if depending on whether the pizza has extra cheese
     @return true or false
     */
    public boolean isExtraCheese() {
        return extraSauce;
    }
}
