package com.example.fahee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        auth=FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(auth.getCurrentUser() != null){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
                else{
                    startActivity(new Intent(SplashScreen.this,SchoolId.class));
                    finish();

                }


            }
        },  5000);

    }
}
