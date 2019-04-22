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
        emailPekerja = findViewById(R.id.emailpekerja);
        nomorTelepon = findViewById(R.id.nomortelpon);
        alamatLengkap = findViewById(R.id.alamatlengkap);
        register = findViewById(R.id.register);
        rgGender = findViewById(R.id.rgGender);
        radioPria = findViewById(R.id.radio_pria);
        radioWanita = findViewById(R.id.radio_wanita);


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
        email = emailPekerja.getText().toString().trim();
        alamat = alamatLengkap.getText().toString().trim();

    }
    public void submitData(){
        Intent intent = new Intent(BuatAkunPekerja.this, VerifyActivity.class);
        intent.putExtra("mobile", mobile);
        intent.putExtra("name",name);
        intent.putExtra("alamat", alamat);
        intent.putExtra("email", email);
        intent.putExtra("func", "registerPk");
        startActivity(intent);
    }
}

