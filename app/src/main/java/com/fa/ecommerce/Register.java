package com.fa.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText InputName, InputUN, InputPass, InputPhone, InputEmail, InputLocation, CP;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InputName = findViewById(R.id.Name);
        InputPass = findViewById(R.id.pass);
        CP = findViewById(R.id.confirmpass);
        InputEmail = findViewById(R.id.Email);
        InputLocation = findViewById(R.id.alamat);
        InputUN = findViewById(R.id.username);
        InputPhone = findViewById(R.id.notelp);
        pd = new ProgressDialog(this);
        Button CA = findViewById(R.id.createaccount);
        CA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.createaccount) {
            createaccount();
        }
    }

    private void createaccount() {

        String nama = InputName.getText().toString();
        String pass = InputPass.getText().toString();
        String confpass = CP.getText().toString();
        String Email = InputEmail.getText().toString();
        String UN = InputUN.getText().toString();
        String telp = InputPhone.getText().toString();
        String alamat = InputLocation.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            InputName.setError("Tolong Masukkan Nama");
        } else if (TextUtils.isEmpty(pass)) {
            InputName.setError("Tolong Masukkan Password");
        } else if (TextUtils.isEmpty(confpass)) {
            InputName.setError("Tolong Konfirmasi Password");
        } else if (TextUtils.isEmpty(UN)) {
            InputName.setError("Tolong Masukkan Username");
        } else if (TextUtils.isEmpty(Email)) {
            InputName.setError("Tolong Masukkan Email");
        } else if (TextUtils.isEmpty(telp)) {
            InputName.setError("Tolong Masukkan No Telp");
        } else if (TextUtils.isEmpty(alamat)) {
            InputName.setError("Tolong Masukkan Alamat");
        } else {
            pd.setTitle("Create Account");
            pd.setMessage("Creating Account ...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            validateeverything(nama, pass, confpass, Email, UN, alamat, telp);

        }
    }

    private void validateeverything(final String nama, final String pass, final String confpass, final String Email, final String UN, final String alamat, final String telp) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child("users").child(telp).exists() && pass.equals(confpass) && !dataSnapshot.child("users").child(Email).exists() && !dataSnapshot.child("users").child(UN).exists()) {
                    HashMap<String, Object> Userdatamap = new HashMap<>();
                    Userdatamap.put("nama", nama);
                    Userdatamap.put("password", pass);
                    Userdatamap.put("username", UN);
                    Userdatamap.put("email", Email);
                    Userdatamap.put("alamat", alamat);
                    Userdatamap.put("telepon", telp);
                    Rootref.child("users").child(UN).updateChildren(Userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();

                                        Intent movetoregis = new Intent(Register.this, LoginActivity.class);
                                        startActivity(movetoregis);
                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(Register.this, "Commit Failed Try Again And Check Your Network", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                } else {

                    if (dataSnapshot.child("users").child(telp).exists()) {

                        Toast.makeText(Register.this, "This " + telp + " Already Exist", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        Toast.makeText(Register.this, "Please Use Another Phone Number", Toast.LENGTH_SHORT).show();
                    }

                    if (!pass.equals(confpass)) {
                        Toast.makeText(Register.this, "Password didn't match ...", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                    if (dataSnapshot.child("users").child(UN).exists()) {
                        Toast.makeText(Register.this, "Username Already Exist ...", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                    if (dataSnapshot.child("users").child(Email).exists()) {
                        Toast.makeText(Register.this, "Email Already Exist ...", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
