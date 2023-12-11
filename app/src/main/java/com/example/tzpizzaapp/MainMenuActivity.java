package com.example.tzpizzaapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 the main menu screen for the app
 @author Tyler Amalfa, Zafar Khan
 */
public class MainMenuActivity extends AppCompatActivity {
    /**
     creates the screen for the main menu
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    /**
     opens the build your own activity screen
     @param view
     */
    public void buildClick(View view) {
        Intent intent = new Intent(this, BuildYourOwnActivity.class);
        startActivity(intent);
    }

    /**
     opens the specialty activity screen
     * @param view
     */
    public void specialtyClick(View view) {
        Intent intent = new Intent(this, SpecialtyActivity.class);
        startActivity(intent);
    }

    /**
     opens the current order activity screen
     * @param view
     */
    public void currentOrderClick(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     opens the store orders activity screen
     * @param view
     */
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