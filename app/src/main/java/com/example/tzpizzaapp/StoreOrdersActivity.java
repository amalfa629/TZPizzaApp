package com.example.tzpizzaapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity {
    Context context;
    List<PizzaItem> items;
    List<String> orderNumbers;
    TextView total;
    RecyclerView pizzaView;
    Spinner orders;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        context = this;
        orders = findViewById(R.id.orders);
        orderNumbers = new ArrayList<String>();
        int currentNumber = StoreOrders.getInstance().getCurrentOrderNumber();
        for(Order o: StoreOrders.getInstance().getOrders()) {
            int number = o.getOrderNumber();
            if(number != currentNumber) orderNumbers.add(String.valueOf(number));
        }
        order = StoreOrders.getInstance().getOrder(Integer.parseInt(orderNumbers.get(0)));
        ArrayAdapter<String> ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderNumbers);
        ordersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orders.setAdapter(ordersAdapter);
        orders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                order = StoreOrders.getInstance().getOrder(Integer.parseInt(orderNumbers.get(position)));
                updatePizzas();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        pizzaView = findViewById(R.id.pizzasStore);
        updatePizzas();
    }
    private void updatePizzas() {
        items = order.getPizzaItemList();
        pizzaView.setLayoutManager(new LinearLayoutManager(this));
        pizzaView.setAdapter(new StoreOrdersActivity.StoreOrdersAdapter(getApplicationContext()));
        total = findViewById(R.id.totalStore);
        String totalString = "$" + String.format("%.2f", order.getTotal());
        total.setText(totalString);
    }
    public void onCancelButtonClick(View view) {
        orderNumbers.remove(String.valueOf(order.getOrderNumber()));
        StoreOrders.getInstance().cancelOrder(order.getOrderNumber());
        if(orderNumbers.isEmpty()) {
            Toast toast = Toast.makeText(this, "No Orders Placed", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
        else {
            order = StoreOrders.getInstance().getOrder(Integer.parseInt(orderNumbers.get(0)));
            updatePizzas();
            orders.setSelection(0);
        }
    }
    private class StoreOrdersAdapter extends RecyclerView.Adapter<StoreOrdersAdapter.PizzaItemViewHolder> {
        private Context context;
        public StoreOrdersAdapter(Context context) {
            this.context = context;
        }
        @NonNull
        @Override
        public PizzaItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PizzaItemViewHolder(LayoutInflater.from(context).inflate(R.layout.pizza_item_view, parent,  false));
        }
        @Override
        public void onBindViewHolder(@NonNull PizzaItemViewHolder holder, int position) {
            Pizza p = items.get(position).getPizza();
            String sauce = p.getSauce().toString().toLowerCase();
            sauce = sauce.substring(0,1).toUpperCase() + sauce.substring(1);
            holder.sauce.setText(sauce);
            StringBuilder toppingList = new StringBuilder();
            for(Topping topping: p.getToppings()) {
                toppingList.append(topping.getName()).append(", ");
            }
            if(p.isExtraSauce()) {
                toppingList.append("Extra Sauce").append(", ");
            }
            if(p.isExtraCheese()) {
                toppingList.append("Extra Cheese").append(", ");
            }
            String tList = String.valueOf(toppingList);
            tList = toppingList.substring(0, toppingList.length() - 2);
            String price = "$" + String.format("%.2f", p.price());
            String size = p.getSize().toString();
            size = size.charAt(0) + size.substring(1).toLowerCase();
            holder.price.setText(price);
            holder.size.setText(size);
            holder.toppingsList.setText(tList);
            holder.pizzaImage.setImageResource(items.get(position).getImage());
            holder.name.setText(items.get(position).getName());
        }
        public class PizzaItemViewHolder extends RecyclerView.ViewHolder {
            ImageView pizzaImage;
            TextView name, sauce, toppingsList, size, price;
            public PizzaItemViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                pizzaImage = itemView.findViewById(R.id.pizzaImage);
                sauce = itemView.findViewById(R.id.sauce);
                toppingsList = itemView.findViewById(R.id.toppingsList);
                size = itemView.findViewById(R.id.size);
                price = itemView.findViewById(R.id.price);
            }
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}