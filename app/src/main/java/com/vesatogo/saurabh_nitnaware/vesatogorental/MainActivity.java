package com.vesatogo.saurabh_nitnaware.vesatogorental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageView splashScreen = findViewById(R.id.splashImage);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);

        splashScreen.startAnimation(animation);


        Thread timer = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent splahIntent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(splahIntent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };

        timer.start();



    }


}



