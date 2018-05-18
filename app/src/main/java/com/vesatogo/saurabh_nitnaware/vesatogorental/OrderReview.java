package com.vesatogo.saurabh_nitnaware.vesatogorental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderReview extends AppCompatActivity {

    static private String TAG="Vesatogo";

    Button mChangeBtn;
    TextView mDurationReview,mLandSize,mTractor,mImplement,mDate,mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);

        Intent value = getIntent();
        String duration = value.getStringExtra("duration");
        String landsize = value.getStringExtra("landsize");
        String date = value.getStringExtra("date");
        String time = value.getStringExtra("time");

        String tractor = value.getStringExtra("tractor");
        String implement = value.getStringExtra("implement");

        mDurationReview = findViewById(R.id.duration_review);
        mDurationReview.setText(duration);

        mTractor = findViewById(R.id.tractor_review);
        mTractor.setText(tractor);

        mImplement = findViewById(R.id.implement_review);
        mImplement.setText(implement);

        mLandSize = findViewById(R.id.land_review);
        mLandSize.setText(landsize);

        mDate = findViewById(R.id.date_review);
        mDate.setText(date);

        mTime = findViewById(R.id.time_review);
        mTime.setText(time);

        mChangeBtn = findViewById(R.id.change_btn);
        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(OrderReview.this,AssestActivity.class);
                startActivity(change);
                finish();
            }
        });

    }
}
