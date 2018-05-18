package com.vesatogo.saurabh_nitnaware.vesatogorental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText farmerName, mobNumber, landSize, villageName;
    Button reggisterBtn, backToLoginBtn;
    private DatabaseReference mDatabaseReference;
    private static int MAX_PHONE_LENGTH = 10;

    public String nameOfFarmer, mob, land, village;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Bundle bundle = getIntent().getExtras();
        String mob_number = bundle.getString("message");
        */
        setContentView(R.layout.activity_register);

        /*
        if(mob_number.equals("8341686868")){
            Intent mainIntent = new Intent(RegisterActivity.this,AssestActivity.class);
            startActivity(mainIntent);
            finish();
        }else {
            setContentView(R.layout.activity_register);
        }
        */

        //Firebase refrence
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReferenceFromUrl("https://vesatogorental" +
                ".firebaseio.com/Users/");



        farmerName = findViewById(R.id.name_farmer);

        mobNumber = findViewById(R.id.mob_number);
        mobNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_PHONE_LENGTH)});

        landSize = findViewById(R.id.land_size);
        villageName = findViewById(R.id.village_name);

        backToLoginBtn = findViewById(R.id.back_btn);
        reggisterBtn = findViewById(R.id.register);

        nameOfFarmer = farmerName.getText().toString();
        mob = mobNumber.getText().toString();
        land = landSize.getText().toString();
        village = villageName.getText().toString();

        if(nameOfFarmer.isEmpty()){
            farmerName.setError("name required!");
        }

        if(mob.isEmpty()){
            mobNumber.setError("mobile number required!");
        }

        if(land.isEmpty()){
            landSize.setError("land size required!");
        }

        if(village.isEmpty()){
            villageName.setError("name of farmer is required!");
        }

        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(back);
                finish();
            }
        });

       reggisterBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"User Data Saved!",Toast.LENGTH_SHORT).show();
               saveFarmersData();
               Intent registerIntent = new Intent(RegisterActivity.this,AssestActivity.class);
               startActivity(registerIntent);
               finish();

           }
       });

    }

    public void saveFarmersData(){

        Log.d("Vesatogo","mob no: " + mob);

        //User user = new User(nameOfFarmer,village,mob,land);

        DatabaseReference ref = mDatabaseReference.push();

        ref.child("Name").setValue(nameOfFarmer);
        ref.child("Mobile Number").setValue(mob);
        ref.child("Land Size").setValue(land);
        ref.child("Village").setValue(village);
        //mDatabaseReference.child("Users").push().setValue(user);

    }


}
