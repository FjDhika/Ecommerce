package com.fa.ecommerce;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button LoginBtn,SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginBtn =  (Button) findViewById(R.id.signin_btn);
        Button SignUpBtn = findViewById(R.id.signup_btn);
        SignUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signup_btn:
                Intent movetoregis = new Intent(MainActivity.this, Register.class);
                startActivity(movetoregis);
        }
    }
}
