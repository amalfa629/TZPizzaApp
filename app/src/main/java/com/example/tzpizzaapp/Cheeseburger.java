package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

public class Cheeseburger extends Pizza {
    public Cheeseburger(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.BACON, Topping.BEEF, Topping.ONION, Topping.TOMATO}));
        sauce = Sauce.KETCHUP;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }
    public double price() {
        double price = 12.99;
        return super.priceHelper(price);
    }
}
