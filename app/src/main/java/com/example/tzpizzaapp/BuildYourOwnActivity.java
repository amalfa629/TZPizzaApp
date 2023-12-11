package com.example.tzpizzaapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 the build your own pizza screen for the app
 @author Tyler Amalfa, Zafar Khan
 */
public class BuildYourOwnActivity extends AppCompatActivity {
    Context context;
    Pizza pizza;
    Size size;
    RadioGroup sizes;
    String defaultSauce;
    Sauce sauce;
    Spinner sauces;
    String defaultAvailable;
    ArrayList<String> availableToppingsList;
    Spinner availableToppings;
    String defaultSelected;
    ArrayList<String> selectedToppingsList;
    Spinner selectedToppings;
    CheckBox extraSauceCheck;
    CheckBox extraCheeseCheck;
    Button order;

    /**
     creates the screen for the build your own pizza page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);
        context = this;
        sizeHelper();
        sauceHelper();
        availableToppingsHelper();
        selectedToppingsHelper();
        extraSauceCheck = findViewById(R.id.extraSauceBYO);
        extraCheeseCheck = findViewById(R.id.extraCheeseBYO);
        order = findViewById(R.id.orderButtonBYO);
    }

    /**
     helper method to initialize the size options
     */
    private void sizeHelper() {
        sizes = findViewById(R.id.sizesBYO);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             checks if the size of the pizza has been changed
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
    }

    /**
     helper method to initialize the dropdown menu for the sauce
     */
    private void sauceHelper() {
        sauces = findViewById(R.id.sauces);
        List<String> saucesList = new ArrayList<String>();
        for(Sauce sauce: Sauce.values()) {
            String lowerSauce = sauce.toString().toLowerCase();
            saucesList.add(lowerSauce.substring(0,1).toUpperCase() + lowerSauce.substring(1));
        }
        defaultSauce = this.getResources().getString(R.string.choose_sauce_string);
        saucesList.add(0, defaultSauce);
        ArrayAdapter<String> saucesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, saucesList);
        saucesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sauces.setAdapter(saucesAdapter);
        sauces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             sets the selected sauce as the pizzas sauce
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sauceName = (String)parent.getItemAtPosition(position);
                if(!sauceName.equals(defaultSauce)) sauce = Sauce.valueOf(sauceName.toUpperCase());
                else sauce = null;
                updatePrice(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    /**
     helper method to initialize the dropdown menu for the toppings
     */
    private void availableToppingsHelper() {
        availableToppings = findViewById(R.id.availableToppings);
        availableToppingsList = new ArrayList<String>();
        for(Topping topping: Topping.values()) {
            availableToppingsList.add(topping.getName());
        }
        defaultAvailable = this.getResources().getString(R.string.add_topping_string);
        availableToppingsList.add(0, defaultAvailable);
        ArrayAdapter<String> availableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableToppingsList);
        availableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        availableToppings.setAdapter(availableAdapter);
        availableToppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             adds selected topping to the pizza
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String topping = (String)parent.getItemAtPosition(position);
                if(!topping.equals(defaultAvailable)) {
                    if(selectedToppingsList.size() <= 7) {
                        availableToppingsList.remove(topping);
                        selectedToppingsList.remove(defaultSelected);
                        selectedToppingsList.add(topping);
                        Collections.sort(selectedToppingsList);
                        selectedToppingsList.add(0, defaultSelected);
                        availableToppings.setSelection(0);
                        selectedToppings.setSelection(0);
                    }
                    else {
                        Toast toast = Toast.makeText(context, "Too Many Toppings", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                updatePrice(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    /**
     initializes the dropdown menu for the selected toppings
     */
    private void selectedToppingsHelper() {
        selectedToppings = findViewById(R.id.selectedToppings);
        selectedToppingsList = new ArrayList<String>();
        defaultSelected = this.getResources().getString(R.string.remove_topping_string);
        selectedToppingsList.add(0, defaultSelected);
        ArrayAdapter<String> selectedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectedToppingsList);
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectedToppings.setAdapter(selectedAdapter);
        selectedToppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             removes selected topping from the pizza
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String topping = (String)parent.getItemAtPosition(position);
                if(!topping.equals(defaultSelected)) {
                    selectedToppingsList.remove(topping);
                    availableToppingsList.remove(defaultAvailable);
                    availableToppingsList.add(topping);
                    Collections.sort(availableToppingsList);
                    availableToppingsList.add(0, defaultAvailable);
                    availableToppings.setSelection(0);
                    selectedToppings.setSelection(0);
                }
                updatePrice(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    /**
     updates the price of the pizza based on selections
     * @param view
     */
    public void updatePrice(View view) {
        if((size != null) && (sauce != null) && (selectedToppingsList.size() > 3)) {
            StringBuilder toppingsString = new StringBuilder();
            for(String t: selectedToppingsList) {
                if(!t.equals(defaultSelected)) toppingsString.append(t).append(';');
            }
            boolean extraSauce = extraSauceCheck.isSelected();
            boolean extraCheese = extraCheeseCheck.isSelected();
            Log.d("A", String.valueOf(sizes.getCheckedRadioButtonId()));
            pizza = PizzaMaker.createPizza("BringYourOwn" + "," + size.toString() + "," + String.valueOf(extraSauce) + "," + String.valueOf(extraCheese) + "," + sauce.toString() + "," + toppingsString);
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
     adds pizza to order
     * @param view
     */
    public void orderPizza(View view) {
        StoreOrders store = StoreOrders.getInstance();
        store.getOrder(store.getCurrentOrderNumber()).addPizza(pizza);
        Toast toast = Toast.makeText(this, "Ordering Pizza...", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}