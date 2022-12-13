package com.example.myplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference soil;
    DatabaseReference temp;
    DatabaseReference lux;

    //TextViews
    TextView soilText;
    TextView tempText;
    TextView luxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        soil = database.getReference("Moisture");
        temp = database.getReference("Temperature");
        lux = database.getReference("Lux");

        soilText = (TextView) findViewById(R.id.soilText);
        tempText = (TextView) findViewById(R.id.tempText);
        luxText = (TextView) findViewById(R.id.luxText);

        updateSoil();
        updateTemp();
        updateLux();
    }

    private void updateSoil() {
        soil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = String.valueOf(snapshot.getValue(Long.class));
                int checkTemp = Integer.parseInt(temp);
                if(checkTemp > 100) {
                    temp = "100";
                }
                soilText.setText("Soil Moisture: " + temp + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateTemp() {
        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = String.valueOf(snapshot.getValue(Long.class));
                tempText.setText("Temperature: " + temp + " °C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateLux() {
        lux.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = String.valueOf(snapshot.getValue(Long.class));
                luxText.setText("Brightness: " + temp + " Lx");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}