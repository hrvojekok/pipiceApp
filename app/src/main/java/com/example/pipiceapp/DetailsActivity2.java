package com.example.pipiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity2 extends AppCompatActivity {


    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextView textViewEkupi1 = findViewById(R.id.prviMobitelEkupi);
        TextView textViewEkupi2 = findViewById(R.id.drugiMobitelEkupi);
        TextView textViewEkupi3 = findViewById(R.id.treciMobitelEkupi);
        TextView textViewEkupi4 = findViewById(R.id.cetvrtiMobitelEkupi);
        TextView textViewEkupi5 = findViewById(R.id.petiMobitelEkupi);

        TextView textViewHgspot1 = findViewById(R.id.prviMobitelHgspot);
        TextView textViewHgspot2 = findViewById(R.id.drugiMobitelHgspot);
        TextView textViewHgspot3 = findViewById(R.id.treciMobitelHgspot);
        TextView textViewHgspot4 = findViewById(R.id.cetvrtiMobitelHgspot);
        TextView textViewHgspot5 = findViewById(R.id.petiMobitelHgspot);


        TextView textViewInstar1 = findViewById(R.id.prviMobitelInstar);
        TextView textViewInstar2 = findViewById(R.id.drugiMobitelInstar);
        TextView textViewInstar3 = findViewById(R.id.treciMobitelInstar);
        TextView textViewInstar4 = findViewById(R.id.cetvrtiMobitelInstar);
        TextView textViewInstar5 = findViewById(R.id.petiMobitelInstar);




        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("basketDatabase", MODE_PRIVATE, null);
        DabaseHelper dabaseHelper = new DabaseHelper(DetailsActivity2.this);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pipiceapp-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference().child("mobiteli");

        Cursor cursor = dabaseHelper.getString(null);


        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {





            }
        }
    }
}













