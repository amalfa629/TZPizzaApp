package com.example.tzpizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyItemViewHolder> {
    Context context;
    List<SpecialtyItem> items;
    public SpecialtyAdapter(Context context, List<SpecialtyItem> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public SpecialtyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecialtyItemViewHolder(LayoutInflater.from(context).inflate(R.layout.specialty_item_view, parent,  false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtyItemViewHolder holder, int position) {
        String sauce = items.get(position).getPizza().getSauce().toString().toLowerCase();
        sauce = sauce.substring(0,1).toUpperCase() + sauce.substring(1);
        holder.sauce.setText(sauce);
        ArrayList<Topping> toppings = items.get(position).getPizza().getToppings();
        StringBuilder toppingList = new StringBuilder();
        for(Topping topping: toppings) {
            toppingList.append(topping.getName()).append(", ");
        }
        String tList = String.valueOf(toppingList);
        tList = toppingList.substring(0, toppingList.length()-2);
        holder.toppingsList.setText(tList);
        holder.pizzaImage.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
