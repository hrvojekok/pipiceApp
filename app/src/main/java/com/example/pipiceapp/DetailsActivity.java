package com.example.pipiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if(intent.getStringExtra("item1") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item1"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item2") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item2"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item3") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item3"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item4") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item4"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item5") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item5"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item6") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item6"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item7") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item7"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item8") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item8"), Toast.LENGTH_LONG).show();
        }
        if(intent.getStringExtra("item9") != null){
            Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item9"), Toast.LENGTH_LONG).show();
        }

        //if(intent != null){
        //    Toast.makeText(DetailsActivity.this, "bla"+ intent.getStringExtra("item1"), Toast.LENGTH_LONG).show();
        //}
    }
}