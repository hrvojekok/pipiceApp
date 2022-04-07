package com.example.pipiceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text1);
        ListView listView = findViewById(R.id.listView);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("andorid");
        arrayList.add("ando2rid");
        arrayList.add("and3orid");
        arrayList.add("ando24rid");
        arrayList.add("ando45rid");
        arrayList.add("andorid");
        arrayList.add("ando2rid");
        arrayList.add("and3orid");
        arrayList.add("ando24rid");
        arrayList.add("ando45rid");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);


        //Toast.makeText(MainActivity.this, "Firebase connected", Toast.LENGTH_LONG).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("languages");


        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String value = snapshot.getValue();

                //Log.d(TAG, "Value is: " + value);
                //Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();

                textView.setText("Text is: " + snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}