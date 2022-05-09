package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView8 = findViewById(R.id.emptyBasket);
        ListView listViewBasket = findViewById(R.id.listViewBasket);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity.this);

        ArrayList<ItemBasket> arrayListBasket = new ArrayList<>();
        ArrayAdapter<ItemBasket> arrayAdapterBasket = new ArrayAdapter<ItemBasket>(this, R.layout.list_item_basket, arrayListBasket);

        listViewBasket.setAdapter(arrayAdapterBasket);
        Cursor cursor = dabaseHelper.getString(null);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");


         if(cursor.moveToFirst()){

            while(cursor.moveToNext()){
                String columnItem = cursor.getString(cursor.getColumnIndex("phone_name"));
                String imageIDItem = cursor.getString(cursor.getColumnIndex("image_id"));
                String priceEkupi = cursor.getString(cursor.getColumnIndex("ekupi_price"));
                String priceHgspot = cursor.getString(cursor.getColumnIndex("hgspot_price"));
                String priceInstar = cursor.getString(cursor.getColumnIndex("instar_price"));


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ItemBasket itemBasket = new ItemBasket(columnItem, imageIDItem, priceEkupi, priceHgspot, priceInstar);
                        arrayListBasket.add(itemBasket);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        ListAdapterBasket listAdapterBasket = new ListAdapterBasket(DetailsActivity.this, arrayListBasket);

        binding.listViewBasket.setAdapter(listAdapterBasket);

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dabaseHelper.dropTable(sqLiteDatabase);
            }
        });
    }
}