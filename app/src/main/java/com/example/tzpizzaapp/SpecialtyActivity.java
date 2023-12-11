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

/**
 the specialty pizza screen for the app
 @author Tyler Amalfa, Zafar Khan
 */
public class SpecialtyActivity extends AppCompatActivity {
    static List<PizzaItem> items;
    static int selectedPos;
    static Pizza pizza;
    Size size;
    RadioGroup sizes;
    RecyclerView specialtyView;
    CheckBox extraSauceCheck;
    CheckBox extraCheeseCheck;
    Button order;

    /**
     creates the screen for the specialty pizza page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        sizes = findViewById(R.id.sizesSpecials);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             checks if the size of the pizza has changed
             * @param radioGroup
             * @param checkedId
             */
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
            /**
             updates price with every selection
             * @param view
             */
            @Override
            public void onClick(View view) {
                updatePrice(view);
            }
        });
        extraSauceCheck = findViewById(R.id.extraSauceSpecial);
        extraCheeseCheck = findViewById(R.id.extraCheeseSpecial);
        order = findViewById(R.id.orderButtonSpecial);
    }

    /**
     updates the price based on pizza selection
     * @param view
     */
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

    /**
     adds pizza to the current order
     * @param view
     */
    public void orderPizza(View view) {
        StoreOrders store = StoreOrders.getInstance();
        store.getOrder(store.getCurrentOrderNumber()).addPizza(pizza);
        Toast toast = Toast.makeText(this, "Ordering Pizza...", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    /**
     contains the recycler
     */
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

        /**
         generates new item holders as you scroll down the view
         * @param holder The ViewHolder which should be updated to represent the contents of the
         *        item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
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

        /**
         holds each individual item
         */
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

            /**
             updates the price of the pizza based on different selections
             */
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

            /**
             selects the specialty pizza holder
             * @param view
             */
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                notifyItemChanged(selectedPos);
                selectedPos = getAdapterPosition();
                notifyItemChanged(selectedPos);
                updatePrice();
            }
        }

        /**
         returns the number of items as an int
         @return number of items
         */
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}