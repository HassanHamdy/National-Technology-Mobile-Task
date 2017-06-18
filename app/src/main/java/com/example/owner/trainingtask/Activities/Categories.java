package com.example.owner.trainingtask.Activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.owner.trainingtask.R;

public class Categories extends AppCompatActivity implements View.OnClickListener {

    private Button BTN1, BTN2, BTN3, BTN4, BTN5, BTN6, BTN7, BTN8, BTN9, BTN10, BTN11, BTN12;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        getSupportActionBar().hide();


        BTN1 = (Button) findViewById(R.id.ALL);
        BTN2 = (Button) findViewById(R.id.appetizers);
        BTN3 = (Button) findViewById(R.id.bread);
        BTN4 = (Button) findViewById(R.id.breakfast);
        BTN5 = (Button) findViewById(R.id.desserts);
        BTN6 = (Button) findViewById(R.id.drinks);
        BTN7 = (Button) findViewById(R.id.maindish);
        BTN8 = (Button) findViewById(R.id.salads);
        BTN9 = (Button) findViewById(R.id.sidedish);
        BTN10 = (Button) findViewById(R.id.soups);
        BTN11 = (Button) findViewById(R.id.marinades);
        BTN12 = (Button) findViewById(R.id.other);


        BTN1.setOnClickListener(this);
        BTN2.setOnClickListener(this);
        BTN3.setOnClickListener(this);
        BTN4.setOnClickListener(this);
        BTN5.setOnClickListener(this);
        BTN6.setOnClickListener(this);
        BTN7.setOnClickListener(this);
        BTN8.setOnClickListener(this);
        BTN9.setOnClickListener(this);
        BTN10.setOnClickListener(this);
        BTN11.setOnClickListener(this);
        BTN12.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {

            switch (v.getId()) {
                case R.id.ALL:
                    startActivity(new Intent(this, MainActivity.class)
                            .putExtra("CAT", ""));
                    break;
                default:
                    startActivity(new Intent(this, MainActivity.class)
                            .putExtra("CAT", ((Button) v).getText().toString()));
                    break;
            }
        } else {
            Toast.makeText(this, "No Internet Connection\ncheck your connection", Toast.LENGTH_SHORT).show();
        }

    }
}
