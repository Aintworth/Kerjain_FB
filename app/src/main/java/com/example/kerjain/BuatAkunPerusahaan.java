package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class BuatAkunPerusahaan extends AppCompatActivity {
    private EditText namaPerusahaan, emailPerusahaan, namaPenanggungjawab, nomorKtp, nomorTelepon, kodePos, nomorNpwp, nomorSiup, alamatLengkap;
    private Spinner provinsi, kotaKabupaten, kecamatan;
    private String namaPer, emailPer, namaPen, ktp, mobile, pos, npwp, siup, provinsi1, kotaKabupaten1, kecamatan1, alamat;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_akun_perusahaan);

        namaPerusahaan = findViewById(R.id.namaperusahaan);
        emailPerusahaan = findViewById(R.id.emailperusahaan);
        namaPenanggungjawab = findViewById(R.id.namapenanggungjawab);
        nomorTelepon = findViewById(R.id.nomortelpon);
        nomorNpwp = findViewById(R.id.nomornpwp);
        nomorSiup = findViewById(R.id.nomorsiup);
        submit = findViewById(R.id.masuk);
        alamatLengkap = findViewById(R.id.alamatlengkap);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
                submitData();
            }
        });
    }

    public void inputData() {
        mobile = nomorTelepon.getText().toString().trim();
        namaPer = namaPerusahaan.getText().toString().trim();
        namaPen = namaPenanggungjawab.getText().toString().trim();
        npwp = nomorNpwp.getText().toString().trim();
        siup = nomorSiup.getText().toString().trim();
        emailPer = emailPerusahaan.getText().toString().trim();
        alamat = alamatLengkap.getText().toString().trim();
    }

    public void submitData(){
        Intent intent = new Intent(BuatAkunPerusahaan.this, VerifyActivity.class);
        intent.putExtra("mobile", mobile);
        intent.putExtra("namaPer",namaPer);
        intent.putExtra("namaPen",namaPen);
        intent.putExtra("emailPer",emailPer);
        intent.putExtra("alamat", alamat);
        intent.putExtra("npwp", npwp);
        intent.putExtra("siup", siup);
        //intent.putExtra("email", email);
        intent.putExtra("func", "registerPr");
        startActivity(intent);
    }
}
