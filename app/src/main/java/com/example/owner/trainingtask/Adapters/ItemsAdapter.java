package com.example.owner.trainingtask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.owner.trainingtask.R;
import com.example.owner.trainingtask.Details.RecipeDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class ItemsAdapter extends ArrayAdapter {

    private int Layout;
    private Context context;
    private ArrayList<RecipeDetails> Recipes;

    public ItemsAdapter(Context context, int resource, ArrayList<RecipeDetails> recipes) {
        super(context, resource, recipes);
        this.context = context;
        this.Layout = resource;
        this.Recipes = recipes;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        viewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(Layout, parent, false);

            viewHolder = new viewHolderItem();
            viewHolder.image = (ImageView) rowView.findViewById(R.id.ListViewImage);
            viewHolder.Title = (TextView) rowView.findViewById(R.id.ListViewTitle);
            viewHolder.Category = (TextView) rowView.findViewById(R.id.ListViewCategory);
            viewHolder.Rate = (RatingBar) rowView.findViewById(R.id.ListViewRating);


            // store the holder with the view.
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (viewHolderItem) convertView.getTag();
        }

        Picasso.with(context).load(Recipes.get(position).getImage())
                .placeholder(R.mipmap.loading)
                .transform(new RoundedCornersTransformation(150, 10))
                .into(viewHolder.image);
        viewHolder.Title.setText(Recipes.get(position).getTitle());
        viewHolder.Category.setText(Recipes.get(position).getCategory());
        viewHolder.Rate.setRating((float) Recipes.get(position).getRate());

        rowView.setTag(viewHolder);
        return rowView;
    }

    private class viewHolderItem {
        ImageView image;
        TextView Title, Category, name, Display, Unit;
        RatingBar Rate;
    }


}
