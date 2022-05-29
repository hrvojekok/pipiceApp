package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pipiceapp.databinding.ActivityDetails2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsActivity2 extends AppCompatActivity {


    ActivityDetails2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetails2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        ListView listViewEkupi = findViewById(R.id.listViewEkupi);
        ListView listViewHgspot = findViewById(R.id.listViewHgspot);
        ListView listViewInstar = findViewById(R.id.listViewInstar);
        TextView textView8 = findViewById(R.id.emptyBasket2);

        TextView textViewUkupnoEkupi = findViewById(R.id.ukupnoEkupi);
        TextView textViewUkupnoHgspot = findViewById(R.id.ukupnoHgspot);
        TextView textViewUkupnoInstar = findViewById(R.id.ukupnoInstar);



        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity2.this);

        ArrayList<ItemBasket2> arrayListBasketEkupi = new ArrayList<>();
        ArrayAdapter<ItemBasket2> arrayAdapterBasketEkupi = new ArrayAdapter<ItemBasket2>(this, R.layout.list_item_basket_item, arrayListBasketEkupi);
        ArrayList<ItemBasket2> arrayListBasketHgspot = new ArrayList<>();
        ArrayAdapter<ItemBasket2> arrayAdapterBasketHgspot = new ArrayAdapter<ItemBasket2>(this, R.layout.list_item_basket_item, arrayListBasketHgspot);
        ArrayList<ItemBasket2> arrayListBasketInstar = new ArrayList<>();
        ArrayAdapter<ItemBasket2> arrayAdapterBasketInstar = new ArrayAdapter<ItemBasket2>(this, R.layout.list_item_basket_item, arrayListBasketInstar);

        listViewEkupi.setAdapter(arrayAdapterBasketEkupi);
        listViewHgspot.setAdapter(arrayAdapterBasketHgspot);
        listViewInstar.setAdapter(arrayAdapterBasketInstar);
        Cursor cursor = dabaseHelper.getString(null);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");

        final int[] price1 = {0};
        final int[] price2 = {0};
        final int[] price3 = {0};

        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String phoneName = cursor.getString(cursor.getColumnIndex("phone_name"));
                @SuppressLint("Range") String imageID = cursor.getString(cursor.getColumnIndex("image_id"));
                @SuppressLint("Range") String priceEkupi = cursor.getString(cursor.getColumnIndex("ekupi_price"));
                @SuppressLint("Range") String priceHgspot = cursor.getString(cursor.getColumnIndex("hgspot_price"));
                @SuppressLint("Range") String priceInstar = cursor.getString(cursor.getColumnIndex("instar_price"));
                reference.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ItemBasket2 itemBasket2ekupi = new ItemBasket2(phoneName, imageID, priceEkupi);
                        arrayListBasketEkupi.add(itemBasket2ekupi);
                        ItemBasket2 itemBasket2hgspot = new ItemBasket2(phoneName, imageID, priceHgspot);
                        arrayListBasketHgspot.add(itemBasket2hgspot);
                        ItemBasket2 itemBasket2instar = new ItemBasket2(phoneName, imageID, priceInstar);
                        arrayListBasketInstar.add(itemBasket2instar);

                        price1[0] = price1[0] + Integer.parseInt(priceEkupi);
                        price2[0] = price2[0] + Integer.parseInt(priceHgspot);
                        price3[0] = price3[0] + Integer.parseInt(priceInstar);

                        textViewUkupnoEkupi.setText(String.valueOf(price1[0]) + " kn");
                        textViewUkupnoHgspot.setText(String.valueOf(price2[0]) + " kn");
                        textViewUkupnoInstar.setText(String.valueOf(price3[0]) + " kn");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        ListAdapterBasket2 listAdapterBasket2ekupi = new ListAdapterBasket2(DetailsActivity2.this, arrayListBasketEkupi);
        binding.listViewEkupi.setAdapter(listAdapterBasket2ekupi);
        ListAdapterBasket2 listAdapterBasket2hgspot = new ListAdapterBasket2(DetailsActivity2.this, arrayListBasketHgspot);
        binding.listViewHgspot.setAdapter(listAdapterBasket2hgspot);
        ListAdapterBasket2 listAdapterBasket2instar = new ListAdapterBasket2(DetailsActivity2.this, arrayListBasketInstar);
        binding.listViewInstar.setAdapter(listAdapterBasket2instar);

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dabaseHelper.dropTable(sqLiteDatabase);
            }
        });
    }
}













