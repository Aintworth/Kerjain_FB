package com.example.kerjain;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class BottomNavigationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        android.support.design.widget.BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //set the HomeFragment as the default fragment to show
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        //show the "kerjain" logo on action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.tulisankerjain);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener navListener = new android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            //case when user tap a fragment
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment(); //this will only create the fragment, not showing it yet
                    break;
                case R.id.nav_pekerjaan:
                    selectedFragment = new PekerjaanFragment();
                    break;
                case R.id.nav_komunitas:
                    selectedFragment = new KomunitasFragment();
                    break;
                case R.id.nav_profil:
                    selectedFragment = new ProfilFragment();
                    break;
            }
            //show the fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };
}
