package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class bank extends AppCompatActivity {

    String amt,lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        Bundle b = getIntent().getExtras();
        amt = b.getString("amount");
        lat = b.getString("latitude");
        lon = b.getString("longitude");
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://retail.onlinesbi.com/retail/login.htm"));
        startActivity(i);
    }
    public void sendSMS(View view) {
        Toast.makeText(this,"Message sent successfully!",Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        String message=amt+" successfully paid!\nPickup at Latitude: "+lat+" and Longitude: "+lon;
        smsManager.sendTextMessage("8825791229",null,message,null,null);
    }
}
