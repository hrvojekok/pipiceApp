package com.example.pipiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DetailsActivity2 extends AppCompatActivity {


    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        ListView listViewEkupi = findViewById(R.id.listViewEkupi);
        ListView listViewHgspot = findViewById(R.id.listViewHgspot);
        ListView listViewInstar = findViewById(R.id.listViewInstar);



        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity2.this);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");

        ArrayList<ItemBasket2> arrayListBasketEkupi = new ArrayList<>();
        ArrayAdapter<ItemBasket2> arrayAdapterBasketEkupi = new ArrayAdapter<ItemBasket2>(this, R.layout.list_item_basket_item, arrayListBasketEkupi);

        listViewEkupi.setAdapter(arrayAdapterBasketEkupi);
        Cursor cursor = dabaseHelper.getString(null);


        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {





            }
        }
    }
}













