package com.example.kerjain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilPekerja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pekerja);

        Button button1  = findViewById(R.id.masuk);
        Button button2  = findViewById(R.id.daftar);

    }

}
