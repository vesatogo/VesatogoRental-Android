package com.vesatogo.saurabh_nitnaware.vesatogorental;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.vesatogo.saurabh_nitnaware.vesatogorental.R.id.back;

public class InputActivity extends AppCompatActivity {

    EditText mDuration, mLandSize;
    Button mPlaceOrderBtn,mPickDateBtn,mPickTimeBtn;
    private TimePickerDialog.OnTimeSetListener mOnTimeSetLisnter;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private static int MAX_LAND_SIZE = 2;
    private static int MAX_DURATION = 5;
    ImageButton mBackBtn;
    public String selectedTractor;
    public String selectedImplement;

    static private String TAG = "Vesatogo";


    String selectedDate,selectedLandSize,selectedDuration,selectedTime;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //Code for Selecting Date
        mPickDateBtn = findViewById(R.id.pick_date);

        mPickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and return it
                DatePickerDialog datePickerDialog = new DatePickerDialog(InputActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth ,mOnDateSetListener,year, month,
                        day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG,"onDateset: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" +
                        year);
                selectedDate = month + "/" + dayOfMonth + "/" + year;
            }
        };

        //Code for selecting time
        mPickTimeBtn = findViewById(R.id.pick_time);

        mPickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(InputActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mOnTimeSetLisnter,hour,
                        minute,false);

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        mOnTimeSetLisnter = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG,"hh:mm " + hourOfDay + ":" + minute);

                selectedTime = updateTime(hourOfDay,minute);

            }
        };



        //Code for back btn
        mBackBtn = findViewById(R.id.back);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_intent = new Intent(InputActivity.this,AssestActivity.class);
                startActivity(back_intent);
                finish();
            }
        });

        //Code for Duration
        mDuration = findViewById(R.id.duration);
        mDuration.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_DURATION)});


        //Code for Land Size
        mLandSize = findViewById(R.id.land_size);
        mLandSize.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LAND_SIZE)});

        selectedLandSize = mLandSize.getText().toString();

        //Getting values from Assest Activity
        Intent value = getIntent();
        selectedTractor = value.getStringExtra("tractor");
        selectedImplement = value.getStringExtra("implement");

        //Code for Place Order
        mPlaceOrderBtn = findViewById(R.id.place_order);
        mPlaceOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedDuration = mDuration.getText().toString();
                selectedLandSize = mLandSize.getText().toString();

                Intent intent = new Intent(InputActivity.this, OrderReview.class);
                intent.putExtra("duration", selectedDuration);
                intent.putExtra("landsize",selectedLandSize);
                intent.putExtra("date",selectedDate);
                intent.putExtra("time",selectedTime);
                intent.putExtra("tractor",selectedTractor);
                intent.putExtra("implement",selectedImplement);
                startActivity(intent);
            }
        });
    }


    public String updateTime(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        }
        else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        }
        else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        String aTime = new StringBuilder().append(hours).append(':').append(minutes).append(" ").append(timeSet).toString();

        return aTime;

    }

}
