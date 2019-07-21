package com.fa.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class notiffragments extends Fragment {

    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;
    TextView profiletext;
    private String userid;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notiffragments,container,false);

        final TextView usernama = view.findViewById(R.id.usrnm);
        final TextView passwordo = view.findViewById(R.id.psswrd);
        final TextView emails = view.findViewById(R.id.maile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("users");
        userid = user.getUid();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String username = dataSnapshot.child(userid).child("username").getValue().toString();
                String password = dataSnapshot.child(userid).child("password").getValue().toString();
                String email = dataSnapshot.child(userid).child("email").getValue().toString();

               usernama.setText("USERNAME : " + username);
               passwordo.setText("PASSWORD : "+ password);
               emails.setText("EMAIL : " + email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(),"oooooh",Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}