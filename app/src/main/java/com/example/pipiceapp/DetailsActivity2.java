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
import com.example.pipiceapp.databinding.ActivityDetailsBinding;
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



        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity2.this);

        ArrayList<ItemBasket2> arrayListBasketEkupi = new ArrayList<>();
        ArrayAdapter<ItemBasket2> arrayAdapterBasketEkupi = new ArrayAdapter<ItemBasket2>(this, R.layout.list_item_basket_item, arrayListBasketEkupi);

        listViewEkupi.setAdapter(arrayAdapterBasketEkupi);
        Cursor cursor = dabaseHelper.getString(null);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");


        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String phoneName = cursor.getString(cursor.getColumnIndex("phone_name"));
                @SuppressLint("Range") String imageID = cursor.getString(cursor.getColumnIndex("image_id"));
                @SuppressLint("Range") String priceEkupi = cursor.getString(cursor.getColumnIndex("ekupi_price"));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ItemBasket2 itemBasket2 = new ItemBasket2(phoneName, imageID, priceEkupi);
                        arrayListBasketEkupi.add(itemBasket2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        }

        ListAdapterBasket2 listAdapterBasket2 = new ListAdapterBasket2(DetailsActivity2.this, arrayListBasketEkupi);

        binding.listViewEkupi.setAdapter(listAdapterBasket2);

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dabaseHelper.dropTable(sqLiteDatabase);
            }
        });
    }
}













