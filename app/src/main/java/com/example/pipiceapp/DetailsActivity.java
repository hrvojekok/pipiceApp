package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView1 = findViewById(R.id.imeTelefona);
        TextView textView2 = findViewById(R.id.firstPrice);
        TextView textView3 = findViewById(R.id.secondPrice);
        TextView textView4 = findViewById(R.id.thirdPrice);
        TextView textView5 = findViewById(R.id.addToBasket1);
        TextView textView6 = findViewById(R.id.addToBasket2);
        TextView textView7 = findViewById(R.id.addToBasket3);

        Intent intent = this.getIntent();

        String[] phoneNameList = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedPreferences sh = getSharedPreferences("SharedPreferences", Context.MODE_MULTI_PROCESS);
                String index = sh.getString("index", "");
                //Toast.makeText(DetailsActivity.this, "image id " + index, Toast.LENGTH_LONG).show();

                String basketItem = String.valueOf(snapshot.child(phoneNameList[Integer.parseInt(index)]).child("phoneName").getValue());
                textView1.setText(basketItem);


                String firstPriceItem = String.valueOf(snapshot.child(phoneNameList[Integer.parseInt(index)]).child("store").child("ekupi").getValue());
                String secondPriceItem = String.valueOf(snapshot.child(phoneNameList[Integer.parseInt(index)]).child("store").child("hgspot").getValue());
                String thirdPriceItem = String.valueOf(snapshot.child(phoneNameList[Integer.parseInt(index)]).child("store").child("instar").getValue());

                textView2.setText(firstPriceItem);
                textView3.setText(secondPriceItem);
                textView4.setText(thirdPriceItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}