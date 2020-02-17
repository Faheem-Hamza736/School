package com.example.fahee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.Signout);

        signout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(auth != null){
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this,Login.class));
                    finishAffinity();
                    finish();

                }

            }
        });


    }
}
