package com.example.tzpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

public class ChickenParm extends Pizza {
    public ChickenParm(Size size, boolean extraSauce, boolean extraCheese) {
        toppings = new ArrayList<Topping>(Arrays.asList(new Topping[]{Topping.FRIED_CHICKEN_CUTLET}));
        sauce = Sauce.VODKA;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }
    public double price() {
        double price = 11.99;
        return super.priceHelper(price);
    }
}
