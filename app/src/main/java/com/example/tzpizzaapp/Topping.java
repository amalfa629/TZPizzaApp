package com.example.tzpizzaapp;

/**
 an enumerated class for the different types of toppings
 @author Tyler Amalfa, Zafar Khan
 */
public enum Topping {
    BACON("Bacon"),
    BEEF("Beef"),
    BLACK_OLIVE("Black Olive"),
    BLUE_CHEESE_CRUMBLES("Blue Cheese Crumbles"),
    CHICKEN("Chicken"),
    CRAB_MEAT("Crab Meat"),
    FRIED_CHICKEN_CUTLET("Fried Chicken Cutlet"),
    GREEN_PEPPER("Green Pepper"),
    HAM("Ham"),
    MUSHROOM("Mushroom"),
    ONION("Onion"),
    PEPPERONI("Pepperoni"),
    PINEAPPLE("Pineapple"),
    SAUSAGE("Sausage"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    TOMATO("Tomato");
    private final String name;
    Topping(String name) {
        this.name = name;
    }

    /**
     returns the name of the topping
     @return returns the name of the topping as a String
     */
    public String getName() {
        return name;
    }
}
