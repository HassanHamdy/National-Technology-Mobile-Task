package com.example.owner.trainingtask.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.owner.trainingtask.Adapters.ItemsAdapter;
import com.example.owner.trainingtask.Details.RecipeDetails;
import com.example.owner.trainingtask.Parsing.JsonParsing;
import com.example.owner.trainingtask.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<RecipeDetails> Res = new ArrayList<RecipeDetails>();
    private TextView textView;
    private String URL;
    public static String API_KEY = "axV15293h59oU9Z853fw48CmI1H1Js";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Catname = getIntent().getStringExtra("CAT");

        if (Catname.equals("")) {
            getSupportActionBar().setTitle("Recipes Item of MixedCategories");
        } else {
            getSupportActionBar().setTitle("Recipes Item of " + Catname);
        }

        listView = (ListView) findViewById(R.id.FirstScreen);
        URL = "https://api2.bigoven.com/recipes?include_primarycat=" + Catname
                + "&api_key=" + API_KEY;

        // menuItems retrieve

        new JsonParsing(MainActivity.this, new JsonParsing.onResponse() {
            @Override
            public void onSuccess(final ArrayList<RecipeDetails> RD) {
                listView.setAdapter(new ItemsAdapter(MainActivity.this, R.layout.first_screen_list_view_item, RD));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, DetailsPage.class)
                                .putExtra("Data", RD.get(position).getID())
                                .putExtra("DataName", RD.get(position).getTitle());
                        startActivity(intent);
                    }
                });
            }
        }).execute(URL);


    }


    //Search & refresh Icons handling

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        MenuItem refresh = menu.findItem(R.id.refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new JsonParsing(MainActivity.this, new JsonParsing.onResponse() {
                    @Override
                    public void onSuccess(final ArrayList<RecipeDetails> RD) {
                        if (RD.size() == 0) {
                            textView = (TextView) findViewById(R.id.suggestion);

                            if (textView.getVisibility() == View.GONE) {
                                listView.setVisibility(listView.GONE);
                                textView.setVisibility(textView.VISIBLE);

                            }
                            textView.setVisibility(textView.VISIBLE);
                            listView.setVisibility(listView.GONE);
                            textView.setText("if you mean That --> ( " + JsonParsing.Text +
                                    " ) Please write it in search Box");

                        } else {
                            if (listView.getVisibility() == View.GONE) {
                                listView.setVisibility(listView.VISIBLE);
                                textView.setVisibility(textView.GONE);

                            }


                            listView.setAdapter(new ItemsAdapter(MainActivity.this, R.layout.first_screen_list_view_item, RD));

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this, DetailsPage.class)
                                            .putExtra("Data", RD.get(position).getID())
                                            .putExtra("DataName", RD.get(position).getTitle());
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).execute(URL);

                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.replaceAll(" ", "%20");
                URL = "https://api2.bigoven.com/recipes?title_kw=" + query +
                        "&api_key=" + API_KEY;
                new JsonParsing(MainActivity.this, new JsonParsing.onResponse() {
                    @Override
                    public void onSuccess(final ArrayList<RecipeDetails> RD) {

                        if (RD.size() == 0) {
                            textView = (TextView) findViewById(R.id.suggestion);

                            if (textView.getVisibility() == View.GONE) {
                                listView.setVisibility(listView.GONE);
                                textView.setVisibility(textView.VISIBLE);

                            }
                            textView.setVisibility(textView.VISIBLE);
                            listView.setVisibility(listView.GONE);
                            textView.setText("if you mean That --> ( " + JsonParsing.Text +
                                    " ) Please write it in search Box");

                        } else {
                            if (listView.getVisibility() == View.GONE) {
                                listView.setVisibility(listView.VISIBLE);
                                textView.setVisibility(textView.GONE);

                            }


                            listView.setAdapter(new ItemsAdapter(MainActivity.this, R.layout.first_screen_list_view_item, RD));

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this, DetailsPage.class)
                                            .putExtra("Data", RD.get(position).getID())
                                            .putExtra("DataName", RD.get(position).getTitle());
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).execute(URL);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }
}
