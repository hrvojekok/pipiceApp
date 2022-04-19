package com.example.pipiceapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pipiceapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = findViewById(R.id.text1);


        ArrayList<Item> arrayList = new ArrayList<>();


        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, R.id.phoneName, arrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);



        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Item item1 = new Item(snapshot.child("Samsung Galaxy S10").toString(), "Add", R.drawable.s21);
                Item item2 = new Item(snapshot.child("Samsung Galaxy S20").toString(), "Add", R.drawable.s21);
                Item item3 = new Item(snapshot.child("Samsung Galaxy S21").toString(), "Add", R.drawable.s21);
                Item item4 = new Item(snapshot.child("Samsung Galaxy S22").toString(), "Add", R.drawable.s21);
                Item item5 = new Item(snapshot.child("Samsung Galaxy S5").toString(), "Add", R.drawable.s21);
                Item item6 = new Item(snapshot.child("Samsung Galaxy S6").toString(), "Add", R.drawable.s21);
                Item item7 = new Item(snapshot.child("Samsung Galaxy S7").toString(), "Add", R.drawable.s21);
                Item item8 = new Item(snapshot.child("Samsung Galaxy S8").toString(), "Add", R.drawable.s21);
                Item item9 = new Item(snapshot.child("Samsung Galaxy S9").toString(), "Add", R.drawable.s21);


                //snapshot.child("Samsung Galaxy S9").child("store").child("ekupi").toString(),
                //        Objects.requireNonNull(snapshot.child("Samsung Galaxy S9").child("store").child("ekupi").getValue()).toString()

                arrayList.add(item1);
                arrayList.add(item2);
                arrayList.add(item3);
                arrayList.add(item4);
                arrayList.add(item5);
                arrayList.add(item6);
                arrayList.add(item7);
                arrayList.add(item8);
                arrayList.add(item9);

                ListAdapter listAdapter = new ListAdapter(MainActivity.this, arrayList);

                binding.listView.setAdapter(listAdapter);
                binding.listView.setClickable(true);
                binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);


                        intent.putExtra("item1", item1.phoneName);
                        intent.putExtra("item2", item2.phoneName);
                        intent.putExtra("item3", item3.phoneName);
                        intent.putExtra("item4", item4.phoneName);
                        intent.putExtra("item5", item5.phoneName);
                        intent.putExtra("item6", item6.phoneName);
                        intent.putExtra("item7", item7.phoneName);
                        intent.putExtra("item8", item8.phoneName);
                        intent.putExtra("item9", item9.phoneName);

                        startActivity(intent);
                    }
                });

//                int numberOfChildren = (int) snapshot.getChildrenCount();
//                int i;
//                for(i = 0; i < numberOfChildren; i++){
//                    textView.setText("Text is: " + numberOfChildren);
//                    //textView.setText("Text is: " + snapshot.child("Samsung Galaxy S10").child("store").child("ekupi").getValue());
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}