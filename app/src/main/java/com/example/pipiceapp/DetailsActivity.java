package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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


        int[] imageID = {R.drawable.s10, R.drawable.s20, R.drawable.s21,
                R.drawable.s22, R.drawable.s5, R.drawable.s6,
                R.drawable.s7, R.drawable.s8, R.drawable.s9};

        String[] phoneName = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};

        ArrayList<Item> arrayList = new ArrayList<>();
        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, R.layout.list_item_basket, R.id.phoneName, arrayList);
        ListView listViewBasket = findViewById(R.id.listViewBasket);
        listViewBasket.setAdapter(arrayAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_MULTI_PROCESS);
                String index = sharedPreferences.getString("index", "");


                Map<String,?> entries = sharedPreferences.getAll();
                Set<String> keys = entries.keySet();
                for (String key : keys) {
                    //Toast.makeText(DetailsActivity.this, "image id " + key, Toast.LENGTH_LONG).show();
                    textView7.setText(key);
                    //Item item = new Item(Objects.requireNonNull(snapshot.child("mobiteli").child(phoneName[Integer.parseInt(key)]).child("phoneName").getValue()).toString(), imageID[Integer.parseInt(key)]);
                    //arrayList.add(item);
                }
                //int numberOfChildren = (int) snapshot.child("mobiteli").getChildrenCount();
                /*for(int i = 0; i < size; i++){
                    String indexItem = sharedPreferences.getString("index", "");
                    Item item = new Item(Objects.requireNonNull(snapshot.child("mobiteli").child(phoneName[i]).child("phoneName").getValue()).toString(), imageID[i]);
                    arrayList.add(item);
                }*/


                //Toast.makeText(DetailsActivity.this, "image id " + index, Toast.LENGTH_LONG).show();

                String basketItem = String.valueOf(snapshot.child(phoneName[Integer.parseInt(index)]).child("phoneName").getValue());
                textView1.setText(basketItem);


                String firstPriceItem = String.valueOf(snapshot.child(phoneName[Integer.parseInt(index)]).child("store").child("ekupi").getValue());
                String secondPriceItem = String.valueOf(snapshot.child(phoneName[Integer.parseInt(index)]).child("store").child("hgspot").getValue());
                String thirdPriceItem = String.valueOf(snapshot.child(phoneName[Integer.parseInt(index)]).child("store").child("instar").getValue());

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