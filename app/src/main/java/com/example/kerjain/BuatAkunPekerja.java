package com.example.kerjain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class BuatAkunPekerja extends AppCompatActivity {
    private EditText namaPekerja, emailPekerja, noKtp, nomorTelepon, kodePos, alamatLengkap;
    private RadioButton radioPria, radioWanita;
    private RadioGroup rgGender;
    private Spinner provinsi, kotaKabupaten, kecamatan;
    private Button register;
    private String gender, mobile, name, ktp, email, pos, alamat, provinsi1, kotaKabupaten1, kecamatan1, func;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_akun_pekerja);

        namaPekerja = findViewById(R.id.namapekerja);
        noKtp = findViewById(R.id.nomorktp);
        emailPekerja = findViewById(R.id.emailpekerja);
        nomorTelepon = findViewById(R.id.nomortelpon);
        kodePos = findViewById(R.id.kodepos);
        alamatLengkap = findViewById(R.id.alamatlengkap);
        register = findViewById(R.id.register);
        rgGender = findViewById(R.id.rgGender);
        radioPria = findViewById(R.id.radio_pria);
        radioWanita = findViewById(R.id.radio_wanita);


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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    inputData();
                    submitData();
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pria:
                if (checked)
                    gender="Pria";
                    break;
            case R.id.radio_wanita:
                if (checked)
                    gender="Wanita";
                    break;
        }
    }
    public void inputData(){
        mobile = nomorTelepon.getText().toString().trim();
        name = namaPekerja.getText().toString().trim();
        ktp = noKtp.getText().toString().trim();
        email = emailPekerja.getText().toString().trim();
        pos = kodePos.getText().toString().trim();
        alamat = alamatLengkap.getText().toString().trim();
        provinsi1 = provinsi.getSelectedItem().toString().trim();
        kotaKabupaten1 = kotaKabupaten.getSelectedItem().toString().trim();
        kecamatan1 = kecamatan.getSelectedItem().toString().trim();
    }
    public void submitData(){
        Intent intent = new Intent(BuatAkunPekerja.this, VerifyActivity.class);
        intent.putExtra("mobile", mobile);
        intent.putExtra("name",name);
        intent.putExtra("alamat", alamat);
        intent.putExtra("provinsi", provinsi1);
        intent.putExtra("kotakabupaten", kotaKabupaten1);
        intent.putExtra("kecamatan", kecamatan1);
        intent.putExtra("ktp", ktp);
        intent.putExtra("email", email);
        intent.putExtra("pos", pos);
        intent.putExtra("func", "register");
        startActivity(intent);
    }
}

