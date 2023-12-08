package com.example.tzpizzaapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {
    TextView orderNumber;
    RecyclerView pizzaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        orderNumber= findViewById(R.id.orderNumber);
        String order = "Order #" + StoreOrders.getInstance().getCurrentOrderNumber();
        orderNumber.setText(order);
        pizzaView = findViewById(R.id.pizzas);
    }
    private class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderActivity.CurrentOrderAdapter.CurrentOrderItemViewHolder> {
        private Context context;
        public CurrentOrderAdapter(Context context) {
            this.context = context;
        }
        @NonNull
        @Override
        public CurrentOrderActivity.CurrentOrderAdapter.CurrentOrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CurrentOrderActivity.CurrentOrderAdapter.CurrentOrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.specialty_item_view, parent,  false));
        }

        @Override
        public void onBindViewHolder(@NonNull CurrentOrderActivity.CurrentOrderAdapter.CurrentOrderItemViewHolder holder, int position) {
            String sauce = items.get(position).getPizza().getSauce().toString().toLowerCase();
            sauce = sauce.substring(0,1).toUpperCase() + sauce.substring(1);
            holder.sauce.setText(sauce);
            ArrayList<Topping> toppings = items.get(position).getPizza().getToppings();
            StringBuilder toppingList = new StringBuilder();
            for(Topping topping: toppings) {
                toppingList.append(topping.getName()).append(", ");
            }
            String tList = String.valueOf(toppingList);
            tList = toppingList.substring(0, toppingList.length() - 2);
            holder.toppingsList.setText(tList);
            holder.pizzaImage.setImageResource(items.get(position).getImage());
            holder.name.setText(items.get(position).getName());
            holder.itemView.setBackgroundColor(selectedPos == position ? Color.LTGRAY : Color.TRANSPARENT);
        }
        public class CurrentOrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView pizzaImage;
            TextView name, sauce, toppingsList;
            public CurrentOrderItemViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                pizzaImage = itemView.findViewById(R.id.pizzaImage);
                sauce = itemView.findViewById(R.id.sauce);
                toppingsList = itemView.findViewById(R.id.toppingsList);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                notifyItemChanged(selectedPos);
                selectedPos = getAdapterPosition();
                notifyItemChanged(selectedPos);
                updatePrice();
            }
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}