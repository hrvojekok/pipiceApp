package com.example.pipiceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Item> implements Filterable {
    public ListAdapter(Context context, ArrayList<Item> arrayList){

        super(context, R.layout.list_item, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Item item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView phoneName = convertView.findViewById(R.id.phoneName);
        imageView.setImageResource(Integer.parseInt(item.imageID));
        phoneName.setText(item.phoneName);


        return convertView;
    }
}
