package com.example.pipiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

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
    ArrayAdapter<Item> arrayAdapter;
    ListAdapter listAdapter;
    AutoCompleteTextView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View basket = findViewById(R.id.basket);
        searchView = findViewById(R.id.searchBar);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_MULTI_PROCESS);

//        int[] imageID = {R.drawable.s10, R.drawable.s20, R.drawable.s21,
//                R.drawable.s22, R.drawable.s5, R.drawable.s6,
//                R.drawable.s7, R.drawable.s8, R.drawable.s9};

        String[] phoneName = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};


        ArrayList<Item> arrayList = new ArrayList<>();
        ArrayList<Item> arrayListTemp = new ArrayList<>();
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity2.class);
                startActivity(intent);
            }
        });

        arrayAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, R.id.phoneName, arrayList);
        //RecyclerView recyclerView = findViewById(R.id.listView);
        //recyclerView.setAdapter(arrayAdapter);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int numberOfChildren = (int) snapshot.child("mobiteli").getChildrenCount();
                for(int i = 0; i < numberOfChildren; i++){
                        Item item = new Item(Objects.requireNonNull(snapshot.child("mobiteli").child(phoneName[i]).child("phoneName").getValue()).toString(),
                                Objects.requireNonNull(snapshot.child("mobiteli").child(phoneName[i]).child("image").getValue()).toString());
                    //String.valueOf(imageID[i])
                        arrayList.add(item);
                }

                listAdapter = new ListAdapter(MainActivity.this, arrayList);

                binding.listView.setAdapter(listAdapter);




                binding.listView.setClickable(true);
                binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String basketItem = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("phoneName").getValue());
                        String basketItemPrice1 = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("store").child("ekupi").getValue());
                        String basketItemPrice2 = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("store").child("hgspot").getValue());
                        String basketItemPrice3 = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("store").child("instar").getValue());
                        String basketItemImage = String.valueOf(snapshot.child("mobiteli").child(phoneName[i]).child("image").getValue());


                        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
                        DabaseHelper dabaseHelper = new DabaseHelper(MainActivity.this);
                        dabaseHelper.addPhone(basketItem, basketItemPrice1, basketItemPrice2, basketItemPrice3, basketItemImage);

                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString(basketItem, basketItem);
                        myEdit.putString("index", String.valueOf(i));
                        myEdit.apply();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(valueEventListener);

        /*
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //(MainActivity.this).listAdapter.getFilter().filter(charSequence);
                int textLength = charSequence.length();
                ArrayList<Item> tempArrayList = new ArrayList<>();
                for(Item item: tempArrayList){
                    if(textLength <= item.getPhoneName().length()){
                        if(charSequence.toString().toLowerCase().contains(item.getPhoneName().toLowerCase())){
                            //item.getPhoneName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            tempArrayList.add(item);
                        }
                    }
                }
                ListAdapter tempListAdapter;
                tempListAdapter = new ListAdapter(MainActivity.this, tempArrayList);
                binding.listView.setAdapter(tempListAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        */
        /*searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<Item> tempArrayList = new ArrayList<>();
                String textToCheck = charSequence.toString();
                if(charSequence.length()!=0){
                    textToCheck = textToCheck.substring(0,1).toUpperCase(Locale.ROOT)+textToCheck.substring(1);
                    for(Item itemTemp: arrayListTemp){
                        if(itemTemp.getPhoneName().startsWith(textToCheck)){
                            tempArrayList.add(itemTemp);
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> items = new ArrayList<>();
        if(snapshot.exists()){

            for(DataSnapshot dataSnapshot: snapshot.child("mobiteli").getChildren()){
                //Item item = new Item(dataSnapshot.child("phoneName").getValue(String.class), (String) dataSnapshot.child("image").getValue());
                String namePhone = dataSnapshot.child("phoneName").getValue(String.class);
                //String imageID = (String) dataSnapshot.child("image").getValue();
                items.add(namePhone);
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_item);
            searchView.setAdapter(arrayAdapter);
        } else {
            Log.d("users", "no data found");
        }
    }
}