package com.example.kerjain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PostPekerjaan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText txt_tgl1, txt_tgl2, txt_jam;
    Button btn_get_datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pekerjaan);

        Spinner spinner = findViewById(R.id.kategori);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategori_job, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        txt_tgl1 = (EditText) findViewById(R.id.txt_tglawal);
        txt_tgl2 = (EditText) findViewById(R.id.txt_tglakhir);
        //btn_get_datetime = (Button) findViewById(R.id.btn_get_datetime);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        txt_tgl1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PostPekerjaan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_tgl2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PostPekerjaan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


//        btn_get_datetime.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(PostPekerjaan.this,
//                        "Tanggal : " + txt_tgl.getText().toString() + "\n" +
//                                "Jam : " + txt_jam.getText().toString()
//                        , Toast.LENGTH_SHORT
//                ).show();
//            }
//        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_tgl1.setText(sdf.format(myCalendar.getTime()));
        txt_tgl2.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

