package com.example.owner.trainingtask.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.owner.trainingtask.Details.Ingredients;
import com.example.owner.trainingtask.R;

import java.util.ArrayList;


public class IngredientAdapter extends ArrayAdapter {

    Context context;
    int Layout;
    ArrayList<Ingredients> ING;


    public IngredientAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Ingredients> ingredient) {
        super(context, resource, ingredient);
        this.context = context;
        this.Layout = resource;
        this.ING = ingredient;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        viewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(Layout, parent, false);

            viewHolder = new viewHolderItem();
            viewHolder.name = (TextView) rowView.findViewById(R.id.NAME);
            viewHolder.Display = (TextView) rowView.findViewById(R.id.DISPLAY);
            viewHolder.Unit = (TextView) rowView.findViewById(R.id.UNIT);


            // store the holder with the view.
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (viewHolderItem) convertView.getTag();
        }

        if (ING.get(position).getName().equals("null") || ING.get(position).getName().equals("")
                || ING.get(position).getName().equals(" ")) {
            viewHolder.name.setText("-");
        } else {
            viewHolder.name.setText(ING.get(position).getName());
        }

        if (ING.get(position).getDisplayQuantity().equals("null") || ING.get(position).getDisplayQuantity().equals("")
                || ING.get(position).getDisplayQuantity().equals(" ")) {
            viewHolder.Display.setText("-");
        } else {
            viewHolder.Display.setText(ING.get(position).getDisplayQuantity());
        }

        if (ING.get(position).getUnit().equals("null") || ING.get(position).getUnit().equals("")
                || ING.get(position).getUnit().equals(" ")) {
            viewHolder.Unit.setText("-");
        } else {
            viewHolder.Unit.setText(ING.get(position).getUnit());
        }

        rowView.setTag(viewHolder);

        return rowView;
    }

    private class viewHolderItem {
        TextView name, Display, Unit;
    }

}
