package com.example.pipiceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Item>{
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

        TextView phoneName = convertView.findViewById(R.id.phoneName);
        TextView storeName = convertView.findViewById(R.id.storeName);
        TextView price = convertView.findViewById(R.id.price);
        phoneName.setText(item.phoneName);
        storeName.setText(item.storeName);
        price.setText(item.price);

        return super.getView(position, convertView, parent);
    }
}
