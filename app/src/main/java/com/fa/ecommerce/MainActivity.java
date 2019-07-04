package com.fa.ecommerce;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.ErrorHandler;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private Button LoginBtn,SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginBtn = findViewById(R.id.signin_btn);
        SignUpBtn = findViewById(R.id.signup_btn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        SignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
