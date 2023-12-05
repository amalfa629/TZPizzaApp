package com.example.tzpizzaapp;

import java.util.ArrayList;

/**
 Creates an order
 @author Tyler Amalfa, Zafar Khan
 */
public class Order {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    private double subtotal;
    private double NJStateTax;
    private double total;
    private static double NJTaxRate = 0.06625;

    /**
     constructor that creates an order given an order number
     @param orderNumber the number of the order
     */
    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        pizzas = new ArrayList<Pizza>();
        subtotal = 0;
        NJStateTax = 0;
        total = 0;
    }

    /**
     adds a pizza to the order
     @param pizza the pizza being added
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        subtotal += pizza.price();
        NJStateTax = NJTaxRate*subtotal;
        total = subtotal + NJStateTax;
    }

    /**
     removes a pizza from the order
     @param pizza pizza being removed
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
        subtotal -= pizza.price();
        NJStateTax = NJTaxRate*subtotal;
        total = subtotal + NJStateTax;
    }

    /**
     gets the order number
     @return returns the order number as an integer
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     gets the list of pizzas in the order
     @return returns the pizzas as an ArrayList
     */
    public ArrayList<Pizza> getPizzaList() {
        return pizzas;
    }

    /**
     returns the pizza as a list of selections which are toppings, price, size, and type
     @returns the pizza string
     */
    public ArrayList<String> getPizzaStringList() {
        ArrayList<String> output = new ArrayList<String >();
        for(Pizza pizza: pizzas) {
            String pizzaType = getTypeString(pizza);
            StringBuilder toppings = new StringBuilder();
            for(Topping topping: pizza.getToppings()) {
                toppings.append(topping.getName()).append(',');
            }
            String sauce = pizza.getSauce().name().toLowerCase();
            toppings.append(sauce.substring(0, 1).toUpperCase()).append(sauce.substring(1)).append(',');
            if(pizza.isExtraSauce()) {
                toppings.append("Extra Sauce,");
            }
            if(pizza.isExtraCheese()) {
                toppings.append("Extra Cheese,");
            }
            String size = pizza.getSize().name().toLowerCase();
            size = size.substring(0,1).toUpperCase() + size.substring(1);
            output.add("[" + size + " " + pizzaType + "] " + toppings + "$" + String.format("%.2f", pizza.price()));
        }
        return output;
    }

    /**
     gets the type of pizza
     @param pizza the pizza
     @return returns the type of pizza as a String
     */
    private static String getTypeString(Pizza pizza) {
        String pizzaType = "BuildYourOwn";
        if(pizza instanceof Deluxe) {
            pizzaType = "Deluxe";
        }
        else if(pizza instanceof Supreme) {
            pizzaType = "Supreme";
        }
        else if(pizza instanceof Meatzza) {
            pizzaType = "Meatzza";
        }
        else if(pizza instanceof Seafood) {
            pizzaType = "Seafood";
        }
        else if(pizza instanceof Pepperoni) {
            pizzaType = "Pepperoni";
        }
        return pizzaType;
    }

    /**
     gets the subtotal of the order
     @return the subtotal as a double
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     returns the NJ state tax
     @return returns the state tax as a double
     */
    public double getNJStateTax() {
        return NJStateTax;
    }

    /**
     returns the total cost of the order
     @return returns the total as a double
     */
    public double getTotal() {
        return total;
    }
}
