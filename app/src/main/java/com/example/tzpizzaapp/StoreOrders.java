package com.example.tzpizzaapp;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 creates an ArrayList that keeps track of the number of orders and what the orders are
 @author Tyler Amalfa, Zafar Khan
 */
public class StoreOrders {
    private static volatile StoreOrders instance;
    private static int currentOrderNumber;
    private ArrayList<Order> orders;
    private StoreOrders() {
        orders = new ArrayList<Order>();
        currentOrderNumber = 0;
        newOrder();
    }
    public static synchronized StoreOrders getInstance() {
        if(instance ==null) {
            synchronized(StoreOrders.class) {
                if(instance == null) instance = new StoreOrders();
            }
        }
        return instance;
    }

    /**
     adds a new order to the ArrayList
     */
    public void newOrder() {
        currentOrderNumber++;
        Order order = new Order(currentOrderNumber);
        orders.add(order);
    }

    /**
     removes an order from the ArrayList
     @param orderNumber the number of the order that is being removed from the ArrayList
     */
    public void cancelOrder(int orderNumber) {
        int index = -1;
        for(Order o: orders) {
            if(o.getOrderNumber() == orderNumber) index = orders.indexOf(o);
        }
        orders.remove(index);
    }

    /**
     adds a pizza to the current order
     @param pizza the pizza being added to the current order
     */
    public void addPizza(Pizza pizza) {
        int index = -1;
        for(Order o: orders) {
            if(o.getOrderNumber() == currentOrderNumber) index = orders.indexOf(o);
        }
        orders.get(index).addPizza(pizza);
    }

    /**
     gets the number of the current order in the ArrayList
     @return returns the current order number as an int
     */
    public int getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    /**
     outputs an order from the ArrayList
     @param orderNumber the number of the order being returned
     @return returns the order
     */
    public Order getOrder(int orderNumber) {
        int index = -1;
        for(Order o: orders) {
            if(o.getOrderNumber() == orderNumber) index = orders.indexOf(o);
        }
        return orders.get(index);
    }

    /**
     returns the orders
     @return returns the orders as an ArrayList of orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     exports the ArrayList of orders to a text file
     */
    public void export() {
        try {
            FileWriter output = new FileWriter("src/main/resources/com/example/tzpizza/orders.txt");
            for (Order o : orders) {
                if(o.getOrderNumber() != currentOrderNumber) {
                    output.write("Order #" + o.getOrderNumber() + "\n");
                    for (String pizza : o.getPizzaStringList()) {
                        output.write(pizza + "\n");
                    }
                }
            }
            output.close();
        }
        catch(Exception ignored) {}
    }
}
