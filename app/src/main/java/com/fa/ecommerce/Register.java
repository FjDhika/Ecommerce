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

import com.fa.ecommerce.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText InputName, InputUN, InputPass, InputPhone, InputEmail, InputLocation, CP;
    private ProgressDialog pd;
    private FirebaseAuth auth;
    private FirebaseUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

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
        } else if(!TextUtils.equals(pass,confpass)){
            CP.setError("Password tidak sama");
        }else {
            pd.setTitle("Create Account");
            pd.setMessage("Creating Account ...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            validateeverything(nama, pass, confpass, Email, UN, alamat, telp);

        }
    }


    private void validateeverything(final String nama, final String pass, final String confpass, final String Email, final String UN, final String alamat, final String telp) {
        final Users user = new Users(nama,telp,pass,UN,alamat,Email,confpass);

        auth.createUserWithEmailAndPassword(Email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            auth.signInWithEmailAndPassword(Email,pass);
                            WriteToDB(user);
                        }else{
                            pd.dismiss();
                            Toast.makeText(Register.this, "Commit Failed Try Again And Check Your Network", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void WriteToDB(Users dataUser) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        newUser = auth.getCurrentUser();

        HashMap<String, Object> Userdatamap = new HashMap<>();
        Userdatamap.put("nama", dataUser.getNama());
        Userdatamap.put("password", dataUser.getPassword());
        Userdatamap.put("username", dataUser.getUsername());
        Userdatamap.put("email", dataUser.getEmail());
        Userdatamap.put("alamat", dataUser.getAlamat());
        Userdatamap.put("telepon", dataUser.getTelepon());

        Rootref.child("users").child(newUser.getUid().toString()).updateChildren(Userdatamap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();


                            Intent movetoregis = new Intent(Register.this, home_activity.class);
                            startActivity(movetoregis);
                            finish();
                        } else {
                            pd.dismiss();
                            Toast.makeText(Register.this, "Commit Failed Try Again And Check Your Network", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}