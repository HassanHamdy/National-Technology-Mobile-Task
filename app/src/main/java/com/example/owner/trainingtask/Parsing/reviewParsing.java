package com.example.owner.trainingtask.Parsing;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.owner.trainingtask.Details.ReviewDetails;

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
 * Created by Owner on 6/17/2017.
 */

public class reviewParsing extends AsyncTask<String, Void, ArrayList<ReviewDetails>> {

    public interface onResponse {
        public void onSuccess(ArrayList<ReviewDetails> RD);
    }


    reviewParsing.onResponse listen;
    private Context context;
    private ArrayList<ReviewDetails> reviewDetail;


    public reviewParsing(Context cont, reviewParsing.onResponse listen) {
        this.context = cont;
        this.listen = listen;

        reviewDetail = new ArrayList<ReviewDetails>();
    }

    @Override
    protected ArrayList<ReviewDetails> doInBackground(String... params) {

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

            try {
                JSONArray JArr = new JSONArray(JSONstr);
                for (int i = 0; i < JArr.length(); ++i) {
                    JSONObject JObject = JArr.getJSONObject(i);
                    String comment = JObject.getString("Comment");
                    double Rate = JObject.getDouble("StarRating");
                    JSONObject Jobj = JObject.getJSONObject("Poster");
                    String UserName = Jobj.getString("UserName");
                    String img = Jobj.getString("PhotoUrl");

                    reviewDetail.add(new ReviewDetails(comment, UserName, img, Rate));
                }

            } catch (JSONException e) {
                e.printStackTrace(); // handle error of JsonArray
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reviewDetail;
    }

    @Override
    protected void onPostExecute(ArrayList<ReviewDetails> reviewDetailses) {
        listen.onSuccess(reviewDetailses);
    }
}
