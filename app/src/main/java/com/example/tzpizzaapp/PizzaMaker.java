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
            case "Buffalo Chicken":
                pizza = new BuffaloChicken(size, extraSauce, extraCheese);
                break;
            case "Cheeseburger":
                pizza = new Cheeseburger(size, extraSauce, extraCheese);
                break;
            case "Chicken Parm":
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

    /**
     Initializes the different types of pizza with their images and adds them to a list of PizzaItems
     @return returns the items
     */
    public static ArrayList<PizzaItem> getPizzaTypes() {
        ArrayList<PizzaItem> items = new ArrayList<PizzaItem>();
        items.add(new PizzaItem("Buffalo Chicken", createPizza("Buffalo Chicken,SMALL,true,true"), R.drawable.buffalochicken));
        items.add(new PizzaItem("Cheeseburger", createPizza("Cheeseburger,SMALL,true,true"), R.drawable.cheeseburger));
        items.add(new PizzaItem("Chicken Parm", createPizza("Chicken Parm,SMALL,true,true"), R.drawable.chickenparm));
        items.add(new PizzaItem("Deluxe", createPizza("Deluxe,SMALL,true,true"), R.drawable.deluxe));
        items.add(new PizzaItem("Hawaiian", createPizza("Hawaiian,SMALL,true,true"), R.drawable.hawaiian));
        items.add(new PizzaItem("Meatzza", createPizza("Meatzza,SMALL,true,true"), R.drawable.meatzza));
        items.add(new PizzaItem("Pepperoni", createPizza("Pepperoni,SMALL,true,true"), R.drawable.pepperoni));
        items.add(new PizzaItem("Seafood", createPizza("Seafood,SMALL,true,true"), R.drawable.seafood));
        items.add(new PizzaItem("Supreme", createPizza("Supreme,SMALL,true,true"), R.drawable.supreme));
        items.add(new PizzaItem("Veggie", createPizza("Veggie,SMALL,true,true"), R.drawable.veggie));
        return items;
    }

    /**
     gets the type of the pizza and returns it as a string
     @param pizza the pizza
     @return the type of the pizza of the string
     */
    public static String getTypeString(Pizza pizza) {
        String pizzaType = "Build Your Own";
        if(pizza instanceof BuffaloChicken) {
            pizzaType = "Buffalo Chicken";
        }
        else if(pizza instanceof Cheeseburger) {
            pizzaType = "Cheeseburger";
        }
        else if(pizza instanceof ChickenParm) {
            pizzaType = "Chicken Parm";
        }
        else if(pizza instanceof Deluxe) {
            pizzaType = "Deluxe";
        }
        else if(pizza instanceof Hawaiian) {
            pizzaType = "Hawaiian";
        }
        else if(pizza instanceof Meatzza) {
            pizzaType = "Meatzza";
        }
        else if(pizza instanceof Pepperoni) {
            pizzaType = "Pepperoni";
        }
        else if(pizza instanceof Seafood) {
            pizzaType = "Seafood";
        }
        else if(pizza instanceof Supreme) {
            pizzaType = "Supreme";
        }
        else if(pizza instanceof Veggie) {
            pizzaType = "Veggie";
        }
        return pizzaType;
    }

    /**
     returns the image of the pizza based on the type
     @param pizza the pizza
     @return returns the image of the pizza
     */
    public static int getTypeImage(Pizza pizza) {
        int pizzaImage = R.drawable.build;
        if(pizza instanceof BuffaloChicken) {
            pizzaImage = R.drawable.buffalochicken;
        }
        else if(pizza instanceof Cheeseburger) {
            pizzaImage = R.drawable.cheeseburger;
        }
        else if(pizza instanceof ChickenParm) {
            pizzaImage = R.drawable.chickenparm;
        }
        else if(pizza instanceof Deluxe) {
            pizzaImage = R.drawable.deluxe;
        }
        else if(pizza instanceof Hawaiian) {
            pizzaImage = R.drawable.hawaiian;
        }
        else if(pizza instanceof Meatzza) {
            pizzaImage = R.drawable.meatzza;
        }
        else if(pizza instanceof Pepperoni) {
            pizzaImage = R.drawable.pepperoni;
        }
        else if(pizza instanceof Seafood) {
            pizzaImage = R.drawable.seafood;
        }
        else if(pizza instanceof Supreme) {
            pizzaImage = R.drawable.supreme;
        }
        else if(pizza instanceof Veggie) {
            pizzaImage = R.drawable.veggie;
        }
        return pizzaImage;
    }
}
