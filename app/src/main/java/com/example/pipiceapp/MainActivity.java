package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pipiceapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        View basket = findViewById(R.id.basket);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_MULTI_PROCESS);

        int[] imageID = {R.drawable.s10, R.drawable.s20, R.drawable.s21,
                R.drawable.s22, R.drawable.s5, R.drawable.s6,
                R.drawable.s7, R.drawable.s8, R.drawable.s9};

        String[] phoneName = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};


        ArrayList<Item> arrayList = new ArrayList<>();

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, R.id.phoneName, arrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        //DatabaseReference reference = database.getReference().child("mobiteli");
        DatabaseReference reference = database.getReference();
        //DatabaseReference referenceBasket = database.getReference().child("mobiteli").child("basket");



        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int numberOfChildren = (int) snapshot.child("mobiteli").getChildrenCount();
                for(int i = 0; i < numberOfChildren; i++){
                        Item item = new Item(Objects.requireNonNull(snapshot.child("mobiteli").child(phoneName[i]).child("phoneName").getValue()).toString(), imageID[i]);
                        arrayList.add(item);
                }

                ListAdapter listAdapter = new ListAdapter(MainActivity.this, arrayList);

                binding.listView.setAdapter(listAdapter);
                binding.listView.setClickable(true);
                binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);



                        String basketItem = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("phoneName").getValue());
                        Toast.makeText(MainActivity.this, "Dodano u košaricu: " + basketItem, Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString(basketItem, basketItem);
                        myEdit.putString("index", String.valueOf(i));
                        myEdit.apply();

                        //addToBasket(phoneName[i], String.valueOf(i));


                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        //startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

/*
    private void addToBasket(String phoneName, String index) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference();

        reference.push();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child("basket").child(String.valueOf(index)).setValue(phoneName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
*/


}