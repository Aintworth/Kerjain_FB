package com.example.kerjain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Komunitas extends AppCompatActivity {

    private static final String TAG = "Komunitas";

    private ImageView mCardImage;
    private TextView mCardTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);
        mCardTitle = (TextView) findViewById(R.id.namakomunitas1);
        mCardImage = (ImageView) findViewById(R.id.fotokomunitas1);

        int imageResource = getResources().getIdentifier("@drawable/mekanik", null, this.getPackageName());
        mCardImage.setImageResource(imageResource);

        mCardTitle.setText("Perkumpulan Mekanik");

        //untuk nampilin tulisan kerjain di action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.tulisankerjain);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
