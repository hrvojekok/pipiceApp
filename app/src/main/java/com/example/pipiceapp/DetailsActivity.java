package com.example.pipiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pipiceapp.databinding.ActivityDetailsBinding;

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


        String[] firstPrice = {"5899", "9001", "9955", "11223", "4334", "5004", "6000", "7000", "7877"};
        String[] secondPrice = {"5987", "8898", "9932", "11993", "4455", "4999", "6550", "6988", "8877"};
        String[] thirdPrice = {"5999", "8992", "9928", "11099", "5000", "5110", "6554", "6779", "7663"};
        String[] phoneNameList = {"Samsung Galaxy S10", "Samsung Galaxy S20", "Samsung Galaxy S21", "Samsung Galaxy S22", "Samsung Galaxy S5", "Samsung Galaxy S6",
                "Samsung Galaxy S7", "Samsung Galaxy S8", "Samsung Galaxy S9"};




        if(intent.getStringExtra("phoneName") != null) {

            String phoneName = intent.getStringExtra("phoneName");

            int index = Arrays.asList(phoneNameList).indexOf(phoneName);
            String itemIDString = intent.getStringExtra("itemNumber");
            //Toast.makeText(DetailsActivity.this, "i: " + itemIDString, Toast.LENGTH_LONG).show();



            textView2.setText(firstPrice[index]);
            textView3.setText(secondPrice[index]);
            textView4.setText(thirdPrice[index]);

            textView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //dodati u košaricu
                    //Toast.makeText(DetailsActivity.this, "dodano u košaricu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DetailsActivity.this, BasketActivity.class);
                    startActivity(intent);
                }
            });
            textView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //dodati u košaricu
                    //Toast.makeText(DetailsActivity.this, "dodano u košaricu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DetailsActivity.this, BasketActivity.class);
                    startActivity(intent);
                }
            });
            textView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //dodati u košaricu
                    //Toast.makeText(DetailsActivity.this, "dodano u košaricu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DetailsActivity.this, BasketActivity.class);
                    startActivity(intent);
                }
            });


            //textView5.setText(itemIDString);

            //int itemNumber = Integer.parseInt(intent.getStringExtra("itemNumber"));
            //String firstPriceString = firstPrice[itemNumber];
            //String secondPriceString = secondPrice[itemNumber];
            //String thirdPriceString = thirdPrice[itemNumber];

            String imageID = intent.getStringExtra("imageID");
            //Toast.makeText(DetailsActivity.this, "image id" + imageID, Toast.LENGTH_LONG).show();

            textView1.setText(phoneName);
            //textView2.setText(firstPriceString);
            //textView3.setText(secondPriceString);
            //textView4.setText(thirdPriceString);
        }



//        if(intent.getStringExtra("itemNumber") != null) {
//            int itemNumber = Integer.parseInt(intent.getStringExtra("itemNumber"));
//            String firstPriceString = firstPrice[itemNumber];
//            String secondPriceString = secondPrice[itemNumber];
//            String thirdPriceString = thirdPrice[itemNumber];
//
//            //textView1.setText(phoneName);
//            textView2.setText(firstPriceString);
//            textView3.setText(secondPriceString);
//            textView4.setText(thirdPriceString);
//        }
    }
}