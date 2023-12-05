package com.example.tzpizzaapp;

/**
 an enumerated class for the different types of toppings
 @author Tyler Amalfa, Zafar Khan
 */
public enum Topping {
    BACON("Bacon"),
    BEEF("Beef"),
    BLACK_OLIVE("Black Olive"),
    CRAB_MEAT("Crab Meat"),
    GREEN_PEPPER("Green Pepper"),
    HAM("Ham"),
    MUSHROOM("Mushroom"),
    ONION("Onion"),
    PEPPERONI("Pepperoni"),
    PINEAPPLE("Pineapple"),
    SAUSAGE("Sausage"),
    SHRIMP("Shrimp"),
    SQUID("Squid");
    private String name;
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
