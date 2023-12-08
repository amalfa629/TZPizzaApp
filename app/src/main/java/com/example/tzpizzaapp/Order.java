package com.example.tzpizzaapp;

import java.util.ArrayList;

/**
 Creates an order
 @author Tyler Amalfa, Zafar Khan
 */
public class Order {
    private int orderNumber;
    private static ArrayList<Pizza> pizzas;
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
    public static ArrayList<PizzaItem> getPizzaItemList() {
        ArrayList<PizzaItem> output = new ArrayList<PizzaItem>();
        for(Pizza pizza: pizzas) {
            String pizzaType = PizzaMaker.getTypeString(pizza);
            int pizzaImage = PizzaMaker.getTypeImage(pizza);
            output.add(new PizzaItem(pizzaType, pizza, pizzaImage));
        }
        return output;
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
