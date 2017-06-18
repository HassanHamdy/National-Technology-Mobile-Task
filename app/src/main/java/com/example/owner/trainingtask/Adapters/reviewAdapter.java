package com.example.owner.trainingtask.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.owner.trainingtask.R;
import com.example.owner.trainingtask.Details.ReviewDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class reviewAdapter extends ArrayAdapter {

    private ArrayList<ReviewDetails> reviewDetail;
    private Context context;
    int Layout;

    public reviewAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<ReviewDetails> Detail) {
        super(context, resource, Detail);
        this.context = context;
        this.Layout = resource;
        this.reviewDetail = Detail;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        viewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(Layout, parent, false);

            viewHolder = new viewHolderItem();
            viewHolder.image = (ImageView) rowView.findViewById(R.id.UserImage);
            viewHolder.name = (TextView) rowView.findViewById(R.id.UserName);
            viewHolder.Comment = (TextView) rowView.findViewById(R.id.comment);
            viewHolder.Rate = (RatingBar) rowView.findViewById(R.id.REVratingBar);


            // store the holder with the view.
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (viewHolderItem) convertView.getTag();
        }


        Picasso.with(context).
                load(reviewDetail.get(position).getImage())
                .placeholder(R.mipmap.user_placeholder)
                .transform(new RoundedCornersTransformation(100, 10))
                .into(viewHolder.image);

        viewHolder.name.setText(reviewDetail.get(position).getUserName());
        viewHolder.Comment.setText(reviewDetail.get(position).getComment());
        viewHolder.Rate.setRating((float) reviewDetail.get(position).getRate());

        rowView.setTag(viewHolder);

        return rowView;

    }

    private class viewHolderItem {
        ImageView image;
        TextView name, Comment;
        RatingBar Rate;
    }
}


