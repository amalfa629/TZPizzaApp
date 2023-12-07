package com.example.tzpizzaapp;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpecialtyActivity extends AppCompatActivity {
    Spinner specials;
    RecyclerView specialtyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        specialtyView = findViewById(R.id.specials);
        ArrayList<SpecialtyItem> items = PizzaMaker.getPizzaTypes();
        specialtyView.setLayoutManager(new LinearLayoutManager(this));
        specialtyView.setAdapter(new SpecialtyAdapter(getApplicationContext(), items));
    }
}