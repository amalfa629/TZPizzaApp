package com.example.tzpizzaapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialtyItemViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    ImageView pizzaImage;
    TextView name, sauce, toppingsList;
    public SpecialtyItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        pizzaImage = itemView.findViewById(R.id.pizzaImage);
        sauce = itemView.findViewById(R.id.sauce);
        toppingsList = itemView.findViewById(R.id.toppingsList);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }
}
