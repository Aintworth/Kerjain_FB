package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity{
    EditText nomorHp;
    Button masuk;
    String func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nomorHp = findViewById(R.id.nomorhandphone);
        masuk = findViewById(R.id.masuk);
        Button button2  = findViewById(R.id.daftar);

        masuk.setOnClickListener(new View.OnClickListener() {
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
    }

    public void userLogin(){
        String mobile = nomorHp.getText().toString().trim();
        Intent intent = new Intent(Login.this, VerifyActivity.class );
        intent.putExtra("func", "login");
        intent.putExtra("mobile", mobile);
        startActivity(intent);
    }

    public void userDaftar(){
        Intent intent = new Intent(this, DaftarSebagai.class);
        startActivity(intent);
    }


}
