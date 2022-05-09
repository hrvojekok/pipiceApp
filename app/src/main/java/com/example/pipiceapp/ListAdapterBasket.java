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

public class ListAdapterBasket extends ArrayAdapter<ItemBasket> {

    public ListAdapterBasket(Context context, ArrayList<ItemBasket> arrayList){

        super(context, R.layout.list_item_basket, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemBasket itemBasket = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_basket, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView phoneName = convertView.findViewById(R.id.phoneNameBasket);
        TextView phonePrice1 = convertView.findViewById(R.id.priceEkupi);
        TextView phonePrice2 = convertView.findViewById(R.id.priceHgspot);
        TextView phonePrice3 = convertView.findViewById(R.id.priceInstar);

        imageView.setImageResource(Integer.parseInt(itemBasket.imageID));
        phoneName.setText(itemBasket.phoneNameBasket);
        phonePrice1.setText(itemBasket.priceEkupi);
        phonePrice2.setText(itemBasket.priceHgspot);
        phonePrice3.setText(itemBasket.priceInstar);
        return convertView;
    }
}
