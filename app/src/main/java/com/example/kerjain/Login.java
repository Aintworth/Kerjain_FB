package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1  = findViewById(R.id.masuk);
        Button button2  = findViewById(R.id.daftar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDaftar();
            }
        });

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.masuk:
                userLogin();
                break;
            case R.id.daftar:
                userDaftar();
                break;
        }
    }

    public void userLogin(){
        Intent intent = new Intent(this, BottomNavigationView.class);
        startActivity(intent);
    }

    public void userDaftar(){
        Intent intent = new Intent(this, DaftarSebagai.class);
        startActivity(intent);
    }


}
