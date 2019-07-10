package com.fa.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Ecommerce3.RecyclerGridAdapter;
import com.fa.ecommerce.Model.barang;

import java.util.ArrayList;
import java.util.List;

public class homefragment extends Fragment {

    List<barang> mylist = new ArrayList<>();
    RecyclerView rcvw;
    RecyclerGridAdapter myAdapterGrid;


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

        RecyclerView rcvw = view.findViewById(R.id.recycler);
        populateList();

        myAdapterGrid = new RecyclerGridAdapter(getActivity(), mylist);
        rcvw.setAdapter(myAdapterGrid);
        rcvw.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void populateList() {
        for (int i = 0; i < 8; i++) {
            barang barang = new barang();

            switch (i) {
                case 0:
                    barang.nmbarang = "Elesis";
                    barang.descbarang = "Red Knight";
                    barang.fotobarang = R.drawable.elesis;
                    mylist.add(barang);
                    break;

                case 1:
                    barang.nmbarang = "Arme";
                    barang.descbarang = "Violet Mage";
                    barang.fotobarang = R.drawable.arme;
                    mylist.add(barang);
                    break;

                case 2:
                    barang.nmbarang = "Edel";
                    barang.descbarang = "Heir Of Frost Family";
                    barang.fotobarang = R.drawable.edel;
                    mylist.add(barang);
                    break;

                case 3:
                    barang.nmbarang = "Lire";
                    barang.descbarang = "Elven Ranger";
                    barang.fotobarang = R.drawable.lire;
                    mylist.add(barang);
                    break;

                case 4:
                    barang.nmbarang = "kopi";
                    barang.descbarang = "uenak anjay";
                    barang.fotobarang = R.mipmap.ic_launcher;
                    mylist.add(barang);
                    break;

                case 5:
                    barang.nmbarang = "Mari";
                    barang.descbarang = "Last Kounat";
                    barang.fotobarang = R.drawable.mari;
                    mylist.add(barang);
                    break;

                case 6:
                    barang.nmbarang = "Ronan";
                    barang.descbarang = "Serdin's Knight";
                    barang.fotobarang = R.drawable.ronan;
                    mylist.add(barang);
                    break;

                case 7:
                    barang.nmbarang = "Ryan";
                    barang.descbarang = "Forest Druid";
                    barang.fotobarang = R.drawable.ryan;
                    mylist.add(barang);
                    break;
            }
        }
    }


}
