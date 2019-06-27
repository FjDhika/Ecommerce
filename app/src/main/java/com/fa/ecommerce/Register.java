package com.fa.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        InputName = (EditText) findViewById(R.id.Name);
        InputPass = (EditText) findViewById(R.id.pass);
        CP = (EditText) findViewById(R.id.confirmpass);
        InputEmail = (EditText) findViewById(R.id.Email);
        InputLocation = (EditText) findViewById(R.id.alamat);
        InputUN = (EditText) findViewById(R.id.username);
        InputPhone = (EditText) findViewById(R.id.notelp);
        pd = new ProgressDialog(this);
        Button CA = findViewById(R.id.createaccount);
        CA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.createaccount:
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
            Toast.makeText(this, "Tolong Masukkan Nama ...", Toast.LENGTH_SHORT);
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
                if (!dataSnapshot.child("user").child(telp).exists()) {
                    HashMap<String, Object> Userdatamap = new HashMap<>();
                    Userdatamap.put("nama", nama);
                    Userdatamap.put("password", pass);
                    Userdatamap.put("confpas", confpass);
                    Userdatamap.put("username", UN);
                    Userdatamap.put("email", Email);
                    Userdatamap.put("alamat", alamat);
                    Userdatamap.put("telepon", telp);
                    Rootref.child("Users").child(telp).updateChildren(Userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();

                                        Intent movetoregis = new Intent(Register.this, LoginActivity.class);
                                        startActivity(movetoregis);
                                    }
                                    else
                                    {
                                        pd.dismiss();
                                        Toast.makeText(Register.this, "Commit Failed Try Again And Check Your Network", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                } else {
                    Toast.makeText(Register.this, "This" + telp + "Already Exist", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    Toast.makeText(Register.this, "Please Use Another Phone Number", Toast.LENGTH_SHORT).show();

                    Intent movetoregis = new Intent(Register.this, MainActivity.class);
                    startActivity(movetoregis);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
