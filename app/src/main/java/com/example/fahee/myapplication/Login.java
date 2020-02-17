package com.example.fahee.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btn;
    private Button register;

    ProgressDialog progressDialog ;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.str_email);
        pass=findViewById(R.id.str_password);
        btn=findViewById(R.id.str_login);
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        register=findViewById(R.id.str_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllValidation();
            }
        });
    }

    private void checkAllValidation() {
        String userEmail = email.getText().toString().trim();
        String userPassword = pass.getText().toString().trim();
        if(TextUtils.isEmpty(userEmail)){
            email.setError("Please Enter Email");
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Please Enter Valid Email");
        }
        else if(TextUtils.isEmpty(userPassword)) {
            email.setError("Please Enter Password");
        }
        else {
            performAuthentication(userEmail,userPassword);
        }

    }

    private void performAuthentication(String userEmail, String userPassword) {
        progressDialog.setMessage("please wait.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Not Authentication",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
