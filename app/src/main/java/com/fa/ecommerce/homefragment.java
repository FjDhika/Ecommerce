package com.fa.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.*;
import androidx.fragment.app.Fragment;

import com.example.Ecommerce3.Interface.MyCallback;
import com.example.Ecommerce3.RecyclerGridAdapter;
import com.fa.ecommerce.Model.barang;
//import com.google.android.material.tabs.TabLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.tabs.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homefragment extends Fragment {
    DatabaseReference productref;

    RecyclerGridAdapter myAdapterGrid;
    private ViewPager viewPager;
    private slideadapter sldadapter;


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
        View view = inflater.inflate(R.layout.homefragments, container, false);
        productref = FirebaseDatabase.getInstance().getReference();

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        final RecyclerView rcvw = view.findViewById(R.id.recycler);

        populateList(new MyCallback() {
            @Override
            public void onCallback(List<barang> value) {
                myAdapterGrid = new RecyclerGridAdapter(getActivity(), value);
                rcvw.setAdapter(myAdapterGrid);

                rcvw.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            }
        });

        sldadapter = new slideadapter(getActivity());

        viewPager.setAdapter(sldadapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
        return view;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void populateList(final MyCallback myCallback) {
        productref.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<barang> mylist = new ArrayList<>();

                for (DataSnapshot dataProduct:dataSnapshot.getChildren()){
                    barang dataBarang = dataProduct.getValue(barang.class);
                    mylist.add(dataBarang);
                }
                myCallback.onCallback(mylist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
