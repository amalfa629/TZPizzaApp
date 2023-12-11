package com.example.tzpizzaapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 the current order screen for the app
 @author Tyler Amalfa, Zafar Khan
 */
public class CurrentOrderActivity extends AppCompatActivity {
    List<PizzaItem> items;
    int selectedPos;
    TextView orderNumber, subtotal, tax, total;
    RecyclerView pizzaView;
    Order order;
    Button removeItem;

    /**
     creates the screen for the current order page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        orderNumber= findViewById(R.id.orderNumber);
        int orderInt = StoreOrders.getInstance().getCurrentOrderNumber();
        String orderString = "Order #" + orderInt;
        orderNumber.setText(orderString);
        order = StoreOrders.getInstance().getOrder(orderInt);
        pizzaView = findViewById(R.id.pizzas);
        items = order.getPizzaItemList();
        selectedPos =  RecyclerView.NO_POSITION;
        pizzaView.setLayoutManager(new LinearLayoutManager(this));
        pizzaView.setAdapter(new CurrentOrderActivity.CurrentOrderAdapter(getApplicationContext()));
        removeItem = findViewById(R.id.removeButton);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        updatePrice();
    }

    /**
     updates the price of the current order
     */
    private void updatePrice() {
        String subtotalString = "$" + String.format("%.2f", order.getSubtotal());
        subtotal.setText(subtotalString);
        String taxString = "$" + String.format("%.2f", order.getNJStateTax());
        tax.setText(taxString);
        String totalString = "$" + String.format("%.2f", order.getTotal());
        total.setText(totalString);
    }

    /**
     removes a pizza from the order
     * @param view
     */
    public void onRemoveButtonClick(View view) {
        order.removePizza(order.getPizzaList().get(selectedPos));
        items = order.getPizzaItemList();
        selectedPos =  RecyclerView.NO_POSITION;
        pizzaView.setLayoutManager(new LinearLayoutManager(this));
        pizzaView.setAdapter(new CurrentOrderActivity.CurrentOrderAdapter(getApplicationContext()));
        removeItem.setEnabled(false);
        updatePrice();
    }

    /**
     adds an order to the store orders
     * @param view
     */
    public void onPlaceButtonClick(View view) {
        if(!order.getPizzaItemList().isEmpty()) {
            StoreOrders.getInstance().newOrder();
            Toast toast = Toast.makeText(this, "Placing order...", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
        else {
            Toast toast = Toast.makeText(this, "No Pizzas in Order", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     contains the recycler
     */
    private class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.PizzaItemViewHolder> {
        private Context context;
        public CurrentOrderAdapter(Context context) {
            this.context = context;
        }
        @NonNull
        @Override
        public PizzaItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PizzaItemViewHolder(LayoutInflater.from(context).inflate(R.layout.pizza_item_view, parent,  false));
        }

        /**
         generates new item holders as you scroll down the view
         * @param holder The ViewHolder which should be updated to represent the contents of the
         *        item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
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
            holder.itemView.setBackgroundColor(selectedPos == position ? Color.LTGRAY : Color.TRANSPARENT);
        }

        /**
         holds each individual item
         */
        public class PizzaItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
                itemView.setOnClickListener(this);
            }

            /**
             selects the PizzaItem holder
             * @param view
             */
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) removeItem.setEnabled(false);
                else {
                    notifyItemChanged(selectedPos);
                    selectedPos = getAdapterPosition();
                    notifyItemChanged(selectedPos);
                    removeItem.setEnabled(true);
                }
            }
        }

        /**
         returns the number of items in the order as an int
         @return number of items
         */
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}