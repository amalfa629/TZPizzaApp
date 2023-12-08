package com.example.tzpizzaapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }
    public void buildClick(View view) {
        Intent intent = new Intent(this, BuildYourOwnActivity.class);
        startActivity(intent);
    }
    public void specialtyClick(View view) {
        Intent intent = new Intent(this, SpecialtyActivity.class);
        startActivity(intent);
    }
    public void currentOrderClick(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }
    public void storeOrdersClick(View view) {
        if(StoreOrders.getInstance().getOrders().size() > 1) {
            Intent intent = new Intent(this, StoreOrdersActivity.class);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(this, "No Orders Placed", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}