package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

public class Hawaiian extends Pizza {
    public Hawaiian(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.HAM, Topping.PINEAPPLE}));
        sauce = Sauce.TOMATO;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }
    public double price() {
        double price = 13.99;
        return super.priceHelper(price);
    }
}
