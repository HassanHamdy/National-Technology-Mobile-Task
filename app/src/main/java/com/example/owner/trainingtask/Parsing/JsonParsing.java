package com.example.owner.trainingtask.Parsing;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.owner.trainingtask.Details.Ingredients;
import com.example.owner.trainingtask.Details.RecipeDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Owner on 6/16/2017.
 */

public class JsonParsing extends AsyncTask<String, Void, ArrayList<RecipeDetails>> {

    public interface onResponse {
        public void onSuccess(ArrayList<RecipeDetails> RD);
    }


    JsonParsing.onResponse listen;
    private Context context;
    public static String Text;

    private ArrayList<RecipeDetails> Data;
    private ArrayList<Ingredients> IData;


    public JsonParsing(Context cont, JsonParsing.onResponse listen) {
        this.context = cont;
        this.listen = listen;

        Data = new ArrayList<RecipeDetails>();
        IData = new ArrayList<Ingredients>();
        Text = new String();
    }


    @Override
    protected ArrayList<RecipeDetails> doInBackground(String... params) {
        String JSONstr = "";
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());//InputStream Returns an estimated number of bytes && BufferedInputStream create buffer by bytes
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)); //reader just create buffer && InputStreamReader just decode byte into chars
            String line = "";

            try {
                while ((line = reader.readLine()) != null) {
                    JSONstr += line;
                }
                Log.d("HASSAN", JSONstr);
                in.close();
            } catch (Exception ex) {

            }

            if (context.getClass().getSimpleName().equals("MainActivity")) {
                try {
                    JSONObject JObj = new JSONObject(JSONstr);
                    int ResultCount = JObj.getInt("ResultCount");

                    if (ResultCount != 0) {
                        JSONArray JArr = JObj.getJSONArray("Results");

                        for (int i = 0; i < JArr.length(); ++i) {
                            JSONObject JObject = JArr.getJSONObject(i);
                            int id = JObject.getInt("RecipeID");
                            String Title = JObject.getString("Title");
                            String category = JObject.getString("Category");
                            double rate = JObject.getDouble("StarRating");
                            String image = JObject.getString("PhotoUrl");


                            Data.add(new RecipeDetails(id, Title, category, image, rate));

                        }
                    } else {
                        String SpellSuggest = JObj.getString("SpellSuggest");
                        Text = SpellSuggest;
                    }


                } catch (JSONException e) {
                    e.printStackTrace(); // handle error of JsonObject
                }
            } else if (context.getClass().getSimpleName().equals("DetailsPage")) {

                try {
                    JSONObject JObject = new JSONObject(JSONstr);
                    int id = JObject.getInt("RecipeID");
                    String Title = JObject.getString("Title");
                    String Description = JObject.getString("Description");
                    String category = JObject.getString("Category");
                    String SubCategory = JObject.getString("Subcategory");
                    double rate = JObject.getDouble("StarRating");
                    String image = JObject.getString("ImageURL");
                    JSONArray JArr = JObject.getJSONArray("Ingredients");
                    for (int i = 0; i < JArr.length(); ++i) {
                        JSONObject JObj = JArr.getJSONObject(i);
                        String name = JObj.getString("Name");
                        String displayQuantity = JObj.getString("DisplayQuantity");
                        String unit = JObj.getString("Unit");
                        IData.add(new Ingredients(name, displayQuantity, unit));
                    }
                    String instructions = JObject.getString("Instructions");

                    Data.add(new RecipeDetails(id, Title, Description, category, SubCategory, image,
                            instructions, rate, IData));

                } catch (JSONException e) {
                    e.printStackTrace(); // handle error of JsonObject
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace(); // handle error of problem in URL
        } catch (IOException e) {
            e.printStackTrace(); // handle error of opening connection
        }
        return Data;
    }

    @Override
    protected void onPostExecute(final ArrayList<RecipeDetails> repoDetailses) {
        listen.onSuccess(repoDetailses);
    }
}
