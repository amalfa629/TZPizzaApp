package com.example.tzpizzaapp;

import java.util.ArrayList;

/**
 a pizza maker class that allows for none of the pizza subclasses to be referenced outside of this class
 @author Tyler Amalfa, Zafar Khan
 */
public class PizzaMaker {
    /**
     creates a pizza from a pizza type
     @param pizzaType the type of pizza being created
     @return returns the newly created pizza
     */
    public static Pizza createPizza(String pizzaType) {
        Pizza pizza = null;
        String[] commands = pizzaType.split(",");
        String type = commands[0];
        Size size = Size.valueOf(commands[1]);
        boolean extraSauce = commands[2].equals("true");
        boolean extraCheese = commands[3].equals("true");
        switch(type) {
            case "Deluxe":
                pizza = new Deluxe(size, extraSauce, extraCheese);
                break;
            case "Supreme":
                pizza = new Supreme(size, extraSauce, extraCheese);
                break;
            case "Meatzza":
                pizza = new Meatzza(size, extraSauce, extraCheese);
                break;
            case "Seafood":
                pizza = new Seafood(size, extraSauce, extraCheese);
                break;
            case "Pepperoni":
                pizza = new Pepperoni(size, extraSauce, extraCheese);
                break;
            case "BringYourOwn":
                Sauce sauce = Sauce.valueOf(commands[4]);
                String[] toppingsArray = commands[5].split(";");
                ArrayList<Topping> toppings = new ArrayList<Topping>();
                for(String t: toppingsArray) {
                    toppings.add(Topping.valueOf(t.replace(' ', '_').toUpperCase()));
                }
                pizza = new BuildYourOwn(toppings, sauce, size, extraSauce, extraCheese);
                break;
        }
        return pizza;
    }
}
