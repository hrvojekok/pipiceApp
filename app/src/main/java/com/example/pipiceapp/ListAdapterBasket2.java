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

public class ListAdapterBasket2 extends ArrayAdapter<ItemBasket2> {
    public ListAdapterBasket2(Context context, ArrayList<ItemBasket2> arrayList){
        super(context, R.layout.list_item_basket_item, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemBasket2 itemBasket = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_basket_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageListView2);
        TextView phoneName = convertView.findViewById(R.id.telefon1);
        TextView phonePrice1 = convertView.findViewById(R.id.telefon1cijena);

        imageView.setImageResource(Integer.parseInt(itemBasket.imageListView2));
        phoneName.setText(itemBasket.telefon1);
        phonePrice1.setText(itemBasket.telefon1cijena);
        return convertView;
    }
}
