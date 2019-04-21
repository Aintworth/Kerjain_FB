package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DaftarSebagai extends AppCompatActivity {

    private ImageButton button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sebagai);
        button1 = findViewById(R.id.btn_perusahaan);
        button2 = findViewById(R.id.btn_pekerja);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masukPerusahaan();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masukPekerja();
            }
        });

    }

    public void masukPekerja(){
        Intent pekerja = new Intent(this, BuatAkunPekerja.class);
        startActivity(pekerja);
    }

    public void masukPerusahaan(){
        Intent perusahaan = new Intent(this, BuatAkunPerusahaan.class);
        startActivity(perusahaan);
    }
}
