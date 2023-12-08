package com.example.tzpizzaapp;

import android.content.Context;
import android.graphics.Color;
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

public class SpecialtyActivity extends AppCompatActivity {
    static List<PizzaItem> items;
    static int selectedPos;
    static Pizza pizza;
    static Size size;
    RadioGroup sizes;
    RecyclerView specialtyView;
    CheckBox extraSauceCheck;
    CheckBox extraCheeseCheck;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        sizes = findViewById(R.id.sizesSpecials);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) size = Size.valueOf(checkedRadioButton.getText().toString().toUpperCase());
                else size = null;
            }
        });
        specialtyView = findViewById(R.id.specials);
        items = PizzaMaker.getPizzaTypes();
        selectedPos =  RecyclerView.NO_POSITION;
        specialtyView.setLayoutManager(new LinearLayoutManager(this));
        specialtyView.setAdapter(new SpecialtyAdapter(getApplicationContext()));
        specialtyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePrice(view);
            }
        });
        extraSauceCheck = findViewById(R.id.extraSauceSpecial);
        extraCheeseCheck = findViewById(R.id.extraCheeseSpecial);
        order = findViewById(R.id.orderButtonSpecial);
    }
    public void updatePrice(View view) {
        if((size != null) && (selectedPos != -1)) {
            boolean extraSauce = extraSauceCheck.isSelected();
            boolean extraCheese = extraCheeseCheck.isSelected();
            pizza = PizzaMaker.createPizza( items.get(selectedPos).getName() + "," + size.toString() + "," + String.valueOf(extraSauce) + "," + String.valueOf(extraCheese));
            String output = "$" + String.format("%.2f", pizza.price()) + " - Add to Order";
            order.setText(output);
            order.setEnabled(true);
        }
        else {
            pizza = null;
            order.setText(R.string.add_string);
            order.setEnabled(false);
        }
    }
    public void orderPizza(View view) {
        StoreOrders store = StoreOrders.getInstance();
        store.getOrder(store.getCurrentOrderNumber()).addPizza(pizza);
        Toast toast = Toast.makeText(this, "Ordering Pizza...", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    private class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.SpecialtyItemViewHolder> {
        private Context context;
        public SpecialtyAdapter(Context context) {
            this.context = context;
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
            tList = toppingList.substring(0, toppingList.length() - 2);
            holder.toppingsList.setText(tList);
            holder.pizzaImage.setImageResource(items.get(position).getImage());
            holder.name.setText(items.get(position).getName());
            holder.itemView.setBackgroundColor(selectedPos == position ? Color.LTGRAY : Color.TRANSPARENT);
        }
        public class SpecialtyItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            private void updatePrice() {
                if((size != null) && (selectedPos != -1)) {
                    boolean extraSauce = extraSauceCheck.isSelected();
                    boolean extraCheese = extraCheeseCheck.isSelected();
                    pizza = PizzaMaker.createPizza( items.get(selectedPos).getName() + "," + size.toString() + "," + String.valueOf(extraSauce) + "," + String.valueOf(extraCheese));
                    String output = "$" + String.format("%.2f", pizza.price()) + " - Add to Order";
                    order.setText(output);
                    order.setEnabled(true);
                }
                else {
                    pizza = null;
                    order.setText(R.string.add_string);
                    order.setEnabled(false);
                }
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