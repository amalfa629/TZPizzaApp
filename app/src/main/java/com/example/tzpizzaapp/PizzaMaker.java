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
            case "BuffaloChicken":
                pizza = new BuffaloChicken(size, extraSauce, extraCheese);
                break;
            case "Cheeseburger":
                pizza = new Cheeseburger(size, extraSauce, extraCheese);
                break;
            case "ChickenParm":
                pizza = new ChickenParm(size, extraSauce,extraCheese);
                break;
            case "Deluxe":
                pizza = new Deluxe(size, extraSauce, extraCheese);
                break;
            case "Hawaiian":
                pizza = new Hawaiian(size, extraSauce, extraCheese);
                break;
            case "Meatzza":
                pizza = new Meatzza(size, extraSauce, extraCheese);
                break;
            case "Pepperoni":
                pizza = new Pepperoni(size, extraSauce, extraCheese);
                break;
            case "Seafood":
                pizza = new Seafood(size, extraSauce, extraCheese);
                break;
            case "Supreme":
                pizza = new Supreme(size, extraSauce, extraCheese);
                break;
            case "Veggie":
                pizza   = new Veggie(size, extraSauce, extraCheese);
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
    public static ArrayList<SpecialtyItem> getPizzaTypes() {
        ArrayList<SpecialtyItem> items = new ArrayList<SpecialtyItem>();
        items.add(new SpecialtyItem("Buffalo Chicken", createPizza("BuffaloChicken,SMALL,true,true"), R.drawable.buffalochicken));
        items.add(new SpecialtyItem("Cheeseburger", createPizza("Cheeseburger,SMALL,true,true"), R.drawable.cheeseburger));
        items.add(new SpecialtyItem("Chicken Parm", createPizza("ChickenParm,SMALL,true,true"), R.drawable.chickenparm));
        items.add(new SpecialtyItem("Deluxe", createPizza("Deluxe,SMALL,true,true"), R.drawable.deluxe));
        items.add(new SpecialtyItem("Hawaiian", createPizza("Hawaiian,SMALL,true,true"), R.drawable.hawaiian));
        items.add(new SpecialtyItem("Meatzza", createPizza("Meatzza,SMALL,true,true"), R.drawable.meatzza));
        items.add(new SpecialtyItem("Pepperoni", createPizza("Pepperoni,SMALL,true,true"), R.drawable.pepperoni));
        items.add(new SpecialtyItem("Seafood", createPizza("Seafood,SMALL,true,true"), R.drawable.seafood));
        items.add(new SpecialtyItem("Supreme", createPizza("Supreme,SMALL,true,true"), R.drawable.supreme));
        items.add(new SpecialtyItem("Veggie", createPizza("Veggie,SMALL,true,true"), R.drawable.veggie));
        return items;
    }
}
