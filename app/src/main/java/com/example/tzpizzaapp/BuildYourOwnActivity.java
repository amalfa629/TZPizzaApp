package com.example.tzpizzaapp;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildYourOwnActivity extends AppCompatActivity {
    String defaultAvailable;
    ArrayList<String> availableToppingList;
    String defaultSelected;
    ArrayList<String> selectedToppingList;
    Spinner sauces;
    Spinner availableToppings;
    Spinner selectedToppings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);
        sauces = findViewById(R.id.sauces);
        availableToppings = findViewById(R.id.availableToppings);
        selectedToppings = findViewById(R.id.selectedToppings);
        List<String> saucesList = new ArrayList<String>();
        availableToppingList = new ArrayList<String>();
        selectedToppingList = new ArrayList<String>();
        for(Sauce sauce: Sauce.values()) {
            String lowerSauce = sauce.toString().toLowerCase();
            saucesList.add(lowerSauce.substring(0,1).toUpperCase() + lowerSauce.substring(1));
        }
        for(Topping topping: Topping.values()) {
            availableToppingList.add(topping.getName());
        }
        saucesList.add(0, this.getResources().getString(R.string.choose_sauce_string));
        ArrayAdapter<String> saucesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, saucesList);
        saucesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sauces.setAdapter(saucesAdapter);
        defaultAvailable = this.getResources().getString(R.string.add_topping_string);
        availableToppingList.add(0, defaultAvailable);
        ArrayAdapter<String> availableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableToppingList);
        availableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        availableToppings.setAdapter(availableAdapter);
        defaultSelected = this.getResources().getString(R.string.remove_topping_string);
        selectedToppingList.add(0, defaultSelected);
        ArrayAdapter<String> selectedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectedToppingList);
        selectedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectedToppings.setAdapter(selectedAdapter);
        availableToppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String topping = (String)parent.getItemAtPosition(position);
                if(!topping.equals(defaultAvailable)) {
                    availableToppingList.remove(topping);
                    selectedToppingList.remove(defaultSelected);
                    selectedToppingList.add(topping);
                    Collections.sort(selectedToppingList);
                    selectedToppingList.add(0, defaultSelected);
                    availableToppings.setSelection(0);
                    selectedToppings.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        selectedToppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String topping = (String)parent.getItemAtPosition(position);
                if(!topping.equals(defaultSelected)) {
                    selectedToppingList.remove(topping);
                    availableToppingList.remove(defaultAvailable);
                    availableToppingList.add(topping);
                    Collections.sort(availableToppingList);
                    availableToppingList.add(0, defaultAvailable);
                    availableToppings.setSelection(0);
                    selectedToppings.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }
}