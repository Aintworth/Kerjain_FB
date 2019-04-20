package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DaftarSebagai extends AppCompatActivity {

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sebagai);

    }

    public void masukPekerja(View view){
        Intent pekerja = new Intent(this, BuatAkunPekerja.class);
        startActivity(pekerja);
    }

    public void masukPerusahaan(View view){
        Intent perusahaan = new Intent(this, BuatAkunPerusahaan.class);
        startActivity(perusahaan);
    }
}
