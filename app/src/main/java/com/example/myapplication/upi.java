package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class upi extends AppCompatActivity {

    TextView t1;
    Button b1;
    String amt,am,lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi);
        t1=findViewById(R.id.textView10);
        Bundle b=getIntent().getExtras();
        amt=b.getString("amount");
        lat = b.getString("latitude");
        lon = b.getString("longitude");
        String test = "Amount:  "+ amt;
        t1.setText(test);
        b1=findViewById(R.id.button2);
    }
    public void sendSMS(View view) {
        Toast.makeText(this,"Message sent successfully!",Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        String message=amt+" successfully paid!\nPickup at Latitude: "+lat+" and Longitude: "+lon;
        smsManager.sendTextMessage("8825791229",null,message,null,null);
    }
}
