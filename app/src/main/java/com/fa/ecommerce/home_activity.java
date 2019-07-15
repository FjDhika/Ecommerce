package com.fa.ecommerce;

import android.os.Bundle;

import com.example.Ecommerce3.RecyclerGridAdapter;
import com.fa.ecommerce.Model.barang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class home_activity extends AppCompatActivity {

    ViewFlipper vf;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadfragment(new homefragment());
                    return true;
                case R.id.navigation_dashboard:
                    loadfragment(new dashboardactivity());
                    return true;
                case R.id.navigation_notifications:
                    loadfragment(new notiffragments());
                    return true;
            }

            return false;
        }
    };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadfragment(new homefragment());

    }

    public void loadfragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameid,fragment);
        transaction.addToBackStack(null);
        transaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out,android.R.animator.fade_in,android.R.animator.fade_out);
        transaction.commit();
    }

}
