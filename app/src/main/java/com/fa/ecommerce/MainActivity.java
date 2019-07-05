package com.fa.ecommerce;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fa.ecommerce.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.ErrorHandler;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private Button LoginBtn,SignUpBtn;
    private SharedPreferences loginPreferences;
    private Boolean saveLogin;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginBtn = findViewById(R.id.signin_btn);
        SignUpBtn = findViewById(R.id.signup_btn);

        loginPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        saveLogin = loginPreferences.getBoolean("saveLogin",false);
        loadingBar = new ProgressDialog(this);

        final String UserName = loginPreferences.getString("PhoneNumber","");
        final String Password = loginPreferences.getString("Password","");
        final String ParentName = loginPreferences.getString("LoginAs","");

        if(saveLogin) {

            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please Wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            LogintoAccount(UserName, Password, ParentName);
        }

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

    private void LogintoAccount(final String username, final String Pass,final String ParentName) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(ParentName).child(username).exists()) {
                    Users userdata = dataSnapshot.child(ParentName).child(username).getValue(Users.class);

                    if (userdata.getUsername().equals(username)) {
                        if (userdata.getPassword().equals(Pass)) {
                            Toast.makeText(MainActivity.this, "Login Success...", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();

                            if (ParentName.equals("Admins")) {
                                Intent intent = new Intent(MainActivity.this, AdminDasboadActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(MainActivity.this, home_activity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Account doesn't exist", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
