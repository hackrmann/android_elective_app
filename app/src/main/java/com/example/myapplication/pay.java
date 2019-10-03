package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class pay extends AppCompatActivity {
    Button pay;
    String amt;
    TextView t1;
    String amount,lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        t1=findViewById(R.id.textView3);
        Bundle b=getIntent().getExtras();
        amount=b.getString("amount");
        lat = b.getString("latitude");
        lon = b.getString("longitude");
        String test = "Amount:  "+ amount;
        t1.setText(test);
        pay=findViewById(R.id.button);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),lat+" "+lon,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),pay2.class);
                i.putExtra("amount",amount);
                i.putExtra("latitude",lat);
                i.putExtra("longitude",lon);
                startActivity(i);

            }
        });
    }
}
