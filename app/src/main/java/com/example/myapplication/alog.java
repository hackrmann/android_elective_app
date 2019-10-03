package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Source{
    Integer cost;

    public Source(Integer cost) {
        this.cost = cost;
    }
}
class Destination{
    Integer cost;

    public Destination(Integer cost) {
        this.cost = cost;
    }
}

class locations{
    public Source source;
    public Destination destination;

    public locations(Source source, Destination destination) {
        this.source = source;
        this.destination = destination;
    }

    public locations() {
    }
}

public class alog extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    FirebaseDatabase mdatabase;
    Button pay, maps;

    double kg = 1.00;
    int cost = 0;
    String lat,lon;

    EditText source, destination, weight;
    TextView bill, distanceView;

    FirebaseUser user;
    FirebaseAuth mauth;
    double distance;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);
        getSupportActionBar().hide();
        maps = findViewById(R.id.mapbut);
        pay = findViewById(R.id.c);
        source = findViewById(R.id.source);
        destination = findViewById(R.id.destination);
        distanceView = findViewById(R.id.distance);

        weight = findViewById(R.id.weight);
        bill = findViewById(R.id.Bill);
        mauth = FirebaseAuth.getInstance();
        user = mauth.getCurrentUser();
        mdatabase = FirebaseDatabase.getInstance();
        DatabaseReference locationref = mdatabase.getReference();

        locationref.child("location").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations list = dataSnapshot.getValue(locations.class);
                System.out.println();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(alog.this, v);
                popup.setOnMenuItemClickListener(alog.this);
                popup.inflate(R.menu.top_menu);
                popup.show();
            }
        });


        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alog.this, MapsActivity2.class);

                startActivityForResult(intent, 42069);
            }
        });

        weight.addTextChangedListener(new TextWatcher() {
                                          @Override
                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                          }

                                          @Override
                                          public void onTextChanged(CharSequence s, int start, int before, int count) {

                                          }

                                          @Override
                                          public void afterTextChanged(Editable s) {

                                              kg = Double.parseDouble(weight.getText().toString());


                                              Log.d("MainActivity", source.getText().toString() + " " + destination.getText().toString() + " " + bill.getText().toString());


                                          }
                                      }


        );

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;

                i = new Intent(getApplicationContext(), pay.class);
                i.putExtra("amount", String.valueOf(cost));
                i.putExtra("latitude",lat);
                i.putExtra("longitude",lon);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(this, mauth.getCurrentUser().getUid() + " Goodbye!!!", Toast.LENGTH_LONG).show();
                mauth.signOut();
                finish();
                return true;

            case R.id.settings:
                Toast.makeText(this, "settings shown", Toast.LENGTH_LONG).show();


            default:
                return false;


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        source.setText("");
        destination.setText("");
//        bill.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        destination = findViewById(R.id.destination);
        source = findViewById(R.id.source);
        if (requestCode == 42069 && resultCode == RESULT_OK && data != null) {
            distance = data.getDoubleExtra("distance", -1);
            lat = data.getStringExtra("latitude");
            lon = data.getStringExtra("longitude");
            Toast.makeText(getApplicationContext(), "success" + String.valueOf(distance), Toast.LENGTH_LONG).show();

            distanceView.setText("Distance is " + distance);
            cost = (int) distance * (int) kg * 8;
            Toast.makeText(getApplicationContext(), String.valueOf(cost), Toast.LENGTH_LONG).show();
            destination.setText(String.valueOf(cost));
            bill.setText(": " + String.valueOf(cost));

            Toast.makeText(getApplicationContext(), "success" + String.valueOf(cost), Toast.LENGTH_LONG).show();
            destination.setText(data.getStringExtra("location"));
            source.setText("Current Location");

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.settings:
                // do your code
                Intent i = new Intent(alog.this,settings.class);
                startActivity(i);
                return true;
            case R.id.logout:
                // do your code
                Toast.makeText(this,mauth.getCurrentUser().getUid()+", Goodbye!",Toast.LENGTH_LONG).show();
                mauth.signOut();
                finish();
//                Intent x = new Intent(alog.this,login.class);
//                startActivity(x);
                return true;

            default:
                return false;
        }
    }
}
