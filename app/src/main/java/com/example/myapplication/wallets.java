package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class wallets extends AppCompatActivity {

    Button b2;
    TextView t1,t2;
    String amt,lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        b2=findViewById(R.id.button3);
        t1=findViewById(R.id.textView14);
        t2=findViewById(R.id.textView15);
        Bundle b=getIntent().getExtras();
        amt=b.getString("amount");
        lat = b.getString("latitude");
        lon = b.getString("longitude");
        String test = "Amount:  "+ amt;
        t1.setText(test);
        t2.setText(test);
    }
    public void sendSMS(View view) {
        Toast.makeText(this,"Message sent successfully!",Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        String message=amt+" successfully paid!\nPickup at Latitude: "+lat+" and Longitude: "+lon;
        smsManager.sendTextMessage("8825791229",null,message,null,null);
    }
}
