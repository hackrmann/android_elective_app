package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class pay2 extends AppCompatActivity {

    Button u,ba,w,cancel;
    String amt,lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay2);
        u=findViewById(R.id.upi);
        ba=findViewById(R.id.bank);
        w=findViewById(R.id.wallets);
        cancel=findViewById(R.id.cancel);
        Bundle b=getIntent().getExtras();
        lat = b.getString("latitude");
        lon = b.getString("longitude");
        amt=b.getString("amount");
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),lat+" "+lon,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(pay2.this,bank.class);
                i.putExtra("amount",amt);
                i.putExtra("latitude",lat);
                i.putExtra("longitude",lon);
                startActivity(i);
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),lat+" "+lon,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(pay2.this,wallets.class);
                i.putExtra("amount",amt);
                i.putExtra("latitude",lat);
                i.putExtra("longitude",lon);
                startActivity(i);
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),lat+" "+lon,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(pay2.this,upi.class);
                i.putExtra("amount",amt);
                i.putExtra("latitude",lat);
                i.putExtra("longitude",lon);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
