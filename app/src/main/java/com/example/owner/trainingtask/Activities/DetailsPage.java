package com.example.owner.trainingtask.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.owner.trainingtask.Adapters.IngredientAdapter;
import com.example.owner.trainingtask.Details.RecipeDetails;
import com.example.owner.trainingtask.Parsing.JsonParsing;
import com.example.owner.trainingtask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class DetailsPage extends AppCompatActivity {

    private TextView title, cat, SubCat, desc, instruct;
    private RatingBar ratingBar;
    private ImageView imageView;
    private Button BTN;
    private ListView listView;
    private String NAME;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_screen);

        final int ID = getIntent().getIntExtra("Data", 0);
        String name = getIntent().getStringExtra("DataName");
        getSupportActionBar().setTitle(name);

        String url = "https://api2.bigoven.com/recipe/" + ID +
                "?api_key=" + MainActivity.API_KEY;

        title = (TextView) findViewById(R.id.Title);
        cat = (TextView) findViewById(R.id.Category);
        SubCat = (TextView) findViewById(R.id.SubCategory);
        desc = (TextView) findViewById(R.id.Description);
        instruct = (TextView) findViewById(R.id.Instructions);
        ratingBar = (RatingBar) findViewById(R.id.Rating);
        imageView = (ImageView) findViewById(R.id.Image);
        listView = (ListView) findViewById(R.id.ING);
        BTN = (Button) findViewById(R.id.reviewButton);


        new JsonParsing(DetailsPage.this, new JsonParsing.onResponse() {
            @Override
            public void onSuccess(ArrayList<RecipeDetails> RD) {
                ReviewsButtonListener(ID, RD.get(0).getTitle());
                Picasso.with(DetailsPage.this)
                        .load(RD.get(0).getImage())
                        .placeholder(R.mipmap.loading)
                        .transform(new RoundedCornersTransformation(250, 10))
                        .into(imageView);


                title.setText(RD.get(0).getTitle());

                if (RD.get(0).getDescription().equals("") || RD.get(0).getDescription().equals(" ")) {
                    desc.setText("Sorry There is no description for this Item");
                } else {
                    desc.setText(RD.get(0).getDescription());
                }

                if (RD.get(0).getInstructions().equals("") || RD.get(0).getDescription().equals(" ")) {
                    instruct.setText("Sorry There is no instructions for this Item");
                } else {
                    instruct.setText(RD.get(0).getInstructions());
                }

                cat.setText(RD.get(0).getCategory());
                SubCat.setText(RD.get(0).getSubCategory());
                ratingBar.setRating((float) RD.get(0).getRate());


                listView.setOnTouchListener(new View.OnTouchListener() {
                    // Setting on Touch Listener for handling the touch inside ScrollView
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // Disallow the touch request for parent scroll on touch of child view
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                });


                listView.setAdapter(new IngredientAdapter(DetailsPage.this, R.layout.ingredient_item, RD.get(0).getIngredient()));
                setListViewHeightBasedOnChildren(listView);


            }
        }).execute(url);


    }


    // handling Review Button

    public void ReviewsButtonListener(final int id, final String name) {

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsPage.this, Reviews.class)
                        .putExtra("ID", id)
                        .putExtra("Name", name);
                startActivity(intent);
            }
        });

    }

    // used to handle scrolling of inner ListView

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RelativeLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

    }


}
