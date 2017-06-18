package com.example.owner.trainingtask.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.owner.trainingtask.Adapters.reviewAdapter;
import com.example.owner.trainingtask.Details.ReviewDetails;
import com.example.owner.trainingtask.Parsing.reviewParsing;
import com.example.owner.trainingtask.R;

import java.util.ArrayList;



public class Reviews extends AppCompatActivity {


    ListView listView;
    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        final int Recipe_ID = getIntent().getIntExtra("Data", 0);
        String name = getIntent().getStringExtra("Name");
        String url = "https://api2.bigoven.com/recipe/" + Recipe_ID +
                "/reviews?api_key=" + MainActivity.API_KEY;

        getSupportActionBar().setTitle(name);
        listView = (ListView) findViewById(R.id.Reviews);
        textView = (TextView) findViewById(R.id.handlingTXT);

        new reviewParsing(Reviews.this, new reviewParsing.onResponse() {
            @Override
            public void onSuccess(ArrayList<ReviewDetails> RD) {

                if (RD.size() == 0) {
                    if (textView.getVisibility() == textView.GONE) {
                        textView.setVisibility(textView.VISIBLE);
                    }
                    listView.setVisibility(listView.GONE);
                    textView.setText("There is No Reviews for this item !!");


                } else {

                    if (listView.getVisibility() == listView.GONE) {
                        listView.setVisibility(listView.VISIBLE);
                    }
                    listView.setAdapter(new reviewAdapter(Reviews.this, R.layout.review_item, RD));

                    textView.setVisibility(textView.GONE);
                }


            }
        }).execute(url);


    }
}
