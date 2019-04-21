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
    EditText namaPerusahaan, emailPerusahaan, namaPenanggungjawab, nomorKtp, nomorTelepon, kodePos, nomorNpwp, nomorSiup;
    Spinner provinsi, kotaKabupaten, kecamatan;
    String namaPer, emailPer, namaPen, ktp, mobile, pos, npwp, siup, provinsi1, kotaKabupaten1, kecamatan1;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_akun_perusahaan);

        namaPerusahaan = findViewById(R.id.namaperusahaan);
        emailPerusahaan = findViewById(R.id.emailpekerja);
        namaPenanggungjawab = findViewById(R.id.namapenanggungjawab);
        nomorKtp = findViewById(R.id.nomorktp);
        nomorTelepon = findViewById(R.id.nomortelpon);
        kodePos = findViewById(R.id.kodepos);
        nomorNpwp = findViewById(R.id.nomornpwp);
        nomorSiup = findViewById(R.id.nomorsiup);

        provinsi = findViewById(R.id.provinsi);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsi.setAdapter(adapter1);

        kotaKabupaten = findViewById(R.id.kotakabupaten);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kabupaten, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kotaKabupaten.setAdapter(adapter2);

        kecamatan = findViewById(R.id.kecamatan);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.kecamatan, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kecamatan.setAdapter(adapter3);

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
        ktp = nomorKtp.getText().toString().trim();
        npwp = nomorNpwp.getText().toString().trim();
        siup = nomorSiup.getText().toString().trim();
        emailPer = emailPerusahaan.getText().toString().trim();
        pos = kodePos.getText().toString().trim();
        //alamat = alamatLengkap.getText().toString().trim();
        provinsi1 = provinsi.getSelectedItem().toString().trim();
        kotaKabupaten1 = kotaKabupaten.getSelectedItem().toString().trim();
        kecamatan1 = kecamatan.getSelectedItem().toString().trim();
    }

    public void submitData(){
        Intent intent = new Intent(BuatAkunPerusahaan.this, VerifyActivity.class);
        intent.putExtra("mobile", mobile);
        intent.putExtra("namaPer",namaPer);
        intent.putExtra("namaPen",namaPen);
        intent.putExtra("emailPer",emailPer);
        //intent.putExtra("alamat", alamat);
        intent.putExtra("provinsi", provinsi1);
        intent.putExtra("kotakabupaten", kotaKabupaten1);
        intent.putExtra("kecamatan", kecamatan1);
        intent.putExtra("ktp", ktp);
        intent.putExtra("npwp", npwp);
        intent.putExtra("siup", siup);
        //intent.putExtra("email", email);
        intent.putExtra("pos", pos);
        intent.putExtra("func", "registerPr");
        startActivity(intent);
    }
}
