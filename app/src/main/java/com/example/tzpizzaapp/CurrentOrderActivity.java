package com.example.tzpizzaapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrderActivity extends AppCompatActivity {
    List<PizzaItem> items;
    int selectedPos;
    TextView orderNumber, subtotal, tax, total;
    RecyclerView pizzaView;
    Order order;
    Button removeItem, placeOrder;
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
        items = Order.getPizzaItemList();
        selectedPos =  RecyclerView.NO_POSITION;
        pizzaView.setLayoutManager(new LinearLayoutManager(this));
        pizzaView.setAdapter(new CurrentOrderActivity.CurrentOrderAdapter(getApplicationContext()));
        removeItem = findViewById(R.id.removeButton);
        placeOrder = findViewById(R.id.placeButton);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
    }
    private void updatePrice() {

    }
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
            holder.price.setText(price);
            holder.toppingsList.setText(tList);
            holder.pizzaImage.setImageResource(items.get(position).getImage());
            holder.name.setText(items.get(position).getName());
            holder.itemView.setBackgroundColor(selectedPos == position ? Color.LTGRAY : Color.TRANSPARENT);
        }
        public class PizzaItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView pizzaImage;
            TextView name, sauce, toppingsList, price;
            public PizzaItemViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                pizzaImage = itemView.findViewById(R.id.pizzaImage);
                sauce = itemView.findViewById(R.id.sauce);
                toppingsList = itemView.findViewById(R.id.toppingsList);
                price = itemView.findViewById(R.id.price);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                notifyItemChanged(selectedPos);
                selectedPos = getAdapterPosition();
                notifyItemChanged(selectedPos);
            }
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}