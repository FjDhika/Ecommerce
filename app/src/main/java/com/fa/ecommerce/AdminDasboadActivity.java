package com.fa.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDasboadActivity extends AppCompatActivity {
    private Button LogoutBtn,LogoutBtn2;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dasboad);
        loginPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        LogoutBtn = (Button) findViewById(R.id.dashboard_logoutBtn);
        LogoutBtn2 = (Button) findViewById(R.id.dashboard_logoutBtn2);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();

                Intent intent = new Intent(AdminDasboadActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LogoutBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDasboadActivity.this,AdminCatActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}