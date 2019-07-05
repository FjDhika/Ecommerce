package com.fa.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fa.ecommerce.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText PhoneNumber, Password;
    private Button LoginBtn;
    private TextView asAdmin,asMember;
    private CheckBox rememberMe;
    private ProgressDialog loadingBar;
    private String ParentName= "users";
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    //private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNumber = (EditText) findViewById(R.id.login_edit_number);
        Password = (EditText) findViewById(R.id.login_edit_password);
        LoginBtn = (Button) findViewById(R.id.login_Login);
        rememberMe = (CheckBox) findViewById(R.id.chk_rememberMe);
        loadingBar = new ProgressDialog(this);

        asAdmin = (TextView) findViewById(R.id.login_asAdmin);
        asMember = (TextView) findViewById(R.id.login_asMember);

        loginPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        //saveLogin = loginPreferences.getBoolean("saveLogin",false);
        /*
        if(saveLogin){
            PhoneNumber.setText(loginPreferences.getString("PhoneNumber",""));
            Password.setText(loginPreferences.getString("Password",""));
            rememberMe.setChecked(true);
        }*/
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view)
            {
                LoginUser();
            }
        });
        asAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ParentName = "Admins";
                asMember.setVisibility(View.VISIBLE);
                asAdmin.setVisibility(View.INVISIBLE);
                LoginBtn.setText("Login As Admin");
            }
        });
        asMember.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ParentName="users";
                asAdmin.setVisibility(View.VISIBLE);
                asMember.setVisibility(View.INVISIBLE);
                LoginBtn.setText("Login");
            }
        });
    }

    private void LoginUser(){
        String Phone = PhoneNumber.getText().toString();
        String Pass = Password.getText().toString();

        if (TextUtils.isEmpty(Phone)){
            Toast.makeText(this,"Please input phone number",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(Pass)){
            Toast.makeText(this,"Please input your Password",Toast.LENGTH_LONG).show();
        }
        else{

            if(rememberMe.isChecked()){
                loginPrefsEditor.putBoolean("saveLogin",true);
                loginPrefsEditor.putString("PhoneNumber",Phone);
                loginPrefsEditor.putString("Password",Pass);
                loginPrefsEditor.putString("LoginAs",ParentName);
                loginPrefsEditor.commit();
            }else{
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please Wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            LogintoAccount(Phone,Pass);
        }
    }

    private void LogintoAccount(final String username, final String Pass){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(ParentName).child(username).exists()){
                    Users userdata = dataSnapshot.child(ParentName).child(username).getValue(Users.class);

                    if(userdata.getUsername().equals(username)){
                        if(userdata.getPassword().equals(Pass)){
                            Toast.makeText(LoginActivity.this,"Login Success...", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();

                            if(ParentName.equals("Admins")){
                                Intent intent = new Intent(LoginActivity.this,AdminDasboadActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent = new Intent(LoginActivity.this,home_activity.class);
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Password Incorrect", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Account doesn't exist",Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}