package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ProfilPerusahaan extends AppCompatActivity implements View.OnClickListener {
    Button button1;
    EditText nama;
    EditText nomorTelpon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);

        Button button1  = findViewById(R.id.btn_verifikasi_akun);
        Button button2  = findViewById(R.id.btn_edit_profil);
        EditText nama = findViewById(R.id.namapekerja);
        EditText nomorTelpon = findViewById(R.id.nomortelpon);
        EditText umur = findViewById(R.id.umur);
        EditText jenisKelamin = findViewById(R.id.jenis_kelamin);
        EditText lokasi = findViewById(R.id.lokasi);
        EditText informasiTambahan = findViewById(R.id.informasi_tambahan);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifikasiAkun();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfil();
            }
        });

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_profil:
                editProfil();
                break;
            case R.id.btn_verifikasi_akun:
                verifikasiAkun();
                break;
        }
    }

    public void editProfil(){
        nama.setEnabled(false);
        nomorTelpon.setEnabled(true);
    }

    public void verifikasiAkun(){
        Intent intent = new Intent(this, VerifikasiAkun.class);
        startActivity(intent);
    }

}
