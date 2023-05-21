package com.example.a23b_11345a_l10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.a23b_11345a_l10.Models.Vehicle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView main_LBL_title;
    private MaterialTextView main_LBL_vehicle;
    private AppCompatEditText main_ET_text;
    private AppCompatEditText main_ET_newPrice;
    private MaterialButton main_BTN_update;
    private MaterialButton main_BTN_updatePrice;
    private MaterialButton main_BTN_save;
    private MaterialButton main_BTN_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
//        updateTitleOnce();
        registerForTitleUpdates();
    }

    private void registerForTitleUpdates() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("title");
        titleRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String newTitle = snapshot.getValue(String.class);
                        main_LBL_title.setText(newTitle);
                        Log.d("Title Changed!", "Title is: " + newTitle);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Canceled", "Failed to load data.", error.toException());
                    }
                }

        );
    }

    private void updateTitleOnce() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("title");
        titleRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String newTitle = snapshot.getValue(String.class);
                        main_LBL_title.setText(newTitle);
                        Log.d("Title Changed!", "Title is: " + newTitle);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Canceled", "Failed to load data.", error.toException());
                    }
                }

        );
    }

    private void initViews() {
        Intent intent = getIntent();
        main_LBL_title.setText(intent.getStringExtra("username"));
        main_BTN_update.setOnClickListener(v -> {
            changeTitle(main_ET_text.getText().toString());
        });
        main_BTN_save.setOnClickListener(v ->{
            saveVehicleToDB();
        });
        main_BTN_load.setOnClickListener(v ->{
            loadVehicleFromDb();
        });
        main_BTN_updatePrice.setOnClickListener(v ->{
            updatePrice();
        });
    }

    private void updatePrice() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference vehiclesRef = db.getReference("vehicles").child("price");
        vehiclesRef.setValue(Long.parseLong(main_ET_newPrice.getText().toString()));
    }

    private void loadVehicleFromDb() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference vehiclesRef = db.getReference("vehicles");
        vehiclesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Vehicle> v = snapshot.getValue(ArrayList.class);
                Vehicle v = snapshot.getValue(Vehicle.class);
                Log.d("TAG", "onDataChange: "+ v);
                main_LBL_vehicle.setText(v.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void saveVehicleToDB() {
        Vehicle vehicle = new Vehicle()
                .setWheelCount(2)
                .setBrand("Kawasaki")
                .setLicensePlate("127-56-202")
                .setType(Vehicle.eType.GASOLINE)
                .setPrice(40_000)
                .setManufactureYear(2019);
        Vehicle vehicle2 = new Vehicle()
                .setWheelCount(4)
                .setBrand("Mazda")
                .setLicensePlate("15-336-66")
                .setType(Vehicle.eType.GASOLINE)
                .setPrice(15_000)
                .setManufactureYear(2008);
//        ArrayList<Vehicle> vehicles = new ArrayList<>();
//        vehicles.add(vehicle);
//        vehicles.add(vehicle2);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference vehiclesRef = db.getReference("vehicles");
        vehiclesRef.setValue(vehicle);
    }

    private void changeTitle(String title) {
        //go and save title on db
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("title");
        titleRef.setValue(title);
    }

    private void findViews() {
        main_LBL_vehicle = findViewById(R.id.main_LBL_vehicle);
        main_LBL_title = findViewById(R.id.main_LBL_title);
        main_ET_text = findViewById(R.id.main_ET_text);
        main_ET_newPrice = findViewById(R.id.main_ET_newPrice);
        main_BTN_update = findViewById(R.id.main_BTN_update);
        main_BTN_updatePrice = findViewById(R.id.main_BTN_updatePrice);
        main_BTN_save = findViewById(R.id.main_BTN_save);
        main_BTN_load = findViewById(R.id.main_BTN_load);
    }
}