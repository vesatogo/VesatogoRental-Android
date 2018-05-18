package com.vesatogo.saurabh_nitnaware.vesatogorental;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class AssestActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private static String TAG = "Vesatogo";
    ImageButton mSignOutBtn, m40HP, m50HP, m60HP, mRotavator, mCultivator, mHarvester, mSprayer,
            mMulching,mPlough;

    public String selectedTractor;
    public String selectedImplement;

    Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assest);


        onSelection();

        //Code for signing out to Login Activity
        mAuth = FirebaseAuth.getInstance();

        mSignOutBtn = findViewById(R.id.sign_out_btn);

        mSignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(AssestActivity.this, LoginActivity.class));
                finish();
            }
        });

        //code for next button
        mNextButton = findViewById(R.id.next_btn);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(AssestActivity.this,InputActivity.class);

                Log.d(TAG,selectedImplement + " " + selectedTractor);

                nextIntent.putExtra("tractor", selectedTractor);
                nextIntent.putExtra("implement",selectedImplement);

                startActivity(nextIntent);
                finish();
            }
        });



    }


    private void onSelection(){
        //Code for Tractor
        m40HP = findViewById(R.id.hp40);
        m50HP = findViewById(R.id.hp50);
        m60HP = findViewById(R.id.hp60);

        mRotavator = findViewById(R.id.rotavator);
        mPlough = findViewById(R.id.plough);
        mSprayer = findViewById(R.id.sprayer);
        mMulching = findViewById(R.id.mulcing);
        mHarvester = findViewById(R.id.harvester);
        mCultivator = findViewById(R.id.cultivator);

        m40HP.setOnClickListener(this);
        m50HP.setOnClickListener(this);
        m60HP.setOnClickListener(this);
        mRotavator.setOnClickListener(this);
        mCultivator.setOnClickListener(this);
        mHarvester.setOnClickListener(this);
        mMulching.setOnClickListener(this);
        mSprayer.setOnClickListener(this);
        mPlough.setOnClickListener(this);


    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.hp40:
                Toast.makeText(getApplicationContext(),"40-45HP",Toast.LENGTH_LONG).show();
                selectedTractor = "40-45HP";
                Log.d(TAG,selectedTractor);
                break;
            case R.id.hp50:
                Toast.makeText(getApplicationContext(),"50-55HP",Toast.LENGTH_LONG).show();
                selectedTractor = "50-55HP";
                break;
            case R.id.hp60:
                Toast.makeText(getApplicationContext(),"60-70HP",Toast.LENGTH_SHORT).show();
                selectedTractor = "60-70HP";
                break;
            case R.id.rotavator:
                Toast.makeText(getApplicationContext(),"Rotavator",Toast.LENGTH_SHORT).show();
                selectedImplement = "Rotavator";
                Log.d(TAG,selectedImplement);
                break;
            case R.id.cultivator:
                Toast.makeText(getApplicationContext(),"Cultivator",Toast.LENGTH_SHORT).show();
                selectedImplement = "Cultivator";
                break;
            case R.id.harvester:
                Toast.makeText(getApplicationContext(),"Harvester",Toast.LENGTH_SHORT).show();
                selectedImplement = "Harvester";
                break;
            case R.id.mulcing:
                Toast.makeText(getApplicationContext(),"Mulcing",Toast.LENGTH_SHORT).show();
                selectedImplement = "Mulcing";
                break;
            case R.id.sprayer:
                Toast.makeText(getApplicationContext(),"Sprayer",Toast.LENGTH_SHORT).show();
                selectedImplement = "Sprayer";
                break;
            case R.id.plough:
                Toast.makeText(getApplicationContext(),"Plough",Toast.LENGTH_SHORT).show();
                selectedImplement = "Plough";
                break;
                default:
                    Toast.makeText(getApplicationContext(),"Select Tractor and Implement",Toast
                            .LENGTH_SHORT).show();
        }


        if(selectedImplement == null){
            Toast.makeText(getApplicationContext(),"Select Implement",Toast
                    .LENGTH_SHORT).show();
        }
        if(selectedTractor==null){
            Toast.makeText(getApplicationContext(),"Select Tractor",Toast
                    .LENGTH_SHORT).show();
        }

        //Log.d(TAG,selectedImplement + " " + selectedTractor);

    }




}
