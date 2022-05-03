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
import android.widget.AdapterView;
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

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TextView textView1 = findViewById(R.id.imeTelefona);
        //TextView textView7 = findViewById(R.id.addToBasket3);
        TextView textView8 = findViewById(R.id.emptyBasket);
        ListView listViewBasket = findViewById(R.id.listViewBasket);

        Intent intent = this.getIntent();


        int[] imageID = {R.drawable.s10, R.drawable.s20, R.drawable.s21,
                R.drawable.s22, R.drawable.s5, R.drawable.s6,
                R.drawable.s7, R.drawable.s8, R.drawable.s9};

        String[] phoneName = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity.this);


        ArrayList<Item> arrayList = new ArrayList<>();
        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, R.layout.list_item_basket, R.id.phoneName, arrayList);
        //listViewBasket.setAdapter(arrayAdapter);


        Cursor cursor = dabaseHelper.getString(null);
        //int column = cursor.getColumnCount();
        String[] columnNames = cursor.getColumnNames();


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");


        //int columnNamesLength = columnNames.length;
        //for(int i = 0; i < columnNamesLength; i++){
            if(cursor.moveToFirst()){

                while(cursor.moveToNext()){
                    String columnItem = cursor.getString(cursor.getColumnIndex("phone_name"));

                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String phoneNameForItem = (String) snapshot.child(columnItem).child("phoneName").getValue();

                            int abs = findIndex(phoneName, phoneNameForItem);

                            Item item = new Item(columnItem, imageID[2]);
                            arrayList.add(item);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        //}
        ListAdapter listAdapter = new ListAdapter(DetailsActivity.this, arrayList);

        binding.listViewBasket.setAdapter(listAdapter);




        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sqLiteDatabase.delete("basketTable", null, null);
                dabaseHelper.dropTable(sqLiteDatabase);
                Toast.makeText(DetailsActivity.this, "Košarica obrisana", Toast.LENGTH_LONG).show();

                //dabaseHelper.addPhone(basketItem, basketItemPrice1, basketItemPrice2, basketItemPrice3);
            }
        });

    }

    // Linear-search function to find the index of an element
    public static int findIndex(String arr[], String t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }
}