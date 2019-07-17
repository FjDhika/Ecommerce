package com.fa.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Ecommerce3.GridAdapterDashboard;
import com.example.Ecommerce3.RecyclerGridAdapter;
import com.fa.ecommerce.Model.dashboardcat;
import com.fa.ecommerce.Model.dashboardcat;

import java.util.ArrayList;
import java.util.List;

public class dashboardactivity extends Fragment {

    List<dashboardcat> mlistdb = new ArrayList<>();
    RecyclerView rcvwdb;
    private GridAdapterDashboard gridAdapterDashboard;
    ImageButton imgButton;
    private View v;

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
        final View view = inflater.inflate(R.layout.dashboardfragments, container, false);

        RecyclerView rcvwdb = view.findViewById(R.id.recyclerdb);

        populateList();

        gridAdapterDashboard = new GridAdapterDashboard(getActivity(), mlistdb);

        rcvwdb.setAdapter(gridAdapterDashboard);
        rcvwdb.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        gridAdapterDashboard.setOnItemClickListener(new GridAdapterDashboard.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                dashboardcat dashboardcat = new dashboardcat();
                dashboardcat.namecat = "Elesis";
                dashboardcat.imgcat = R.drawable.arme;
                mlistdb.add(dashboardcat);
                gridAdapterDashboard.notifyItemChanged(position);
            }
        });


        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void populateList() {
        for (int i = 0; i < 16; i++) {
            dashboardcat dashboardcat = new dashboardcat();

            switch (i) {
                case 0:
                    dashboardcat.namecat = "Elesis";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 1:
                    dashboardcat.namecat = "Arme";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 2:
                    dashboardcat.namecat = "Edel";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 3:
                    dashboardcat.namecat = "Lire";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 4:
                    dashboardcat.namecat = "kopi";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 5:
                    dashboardcat.namecat = "Mari";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 6:
                    dashboardcat.namecat = "Ronan";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 7:
                    dashboardcat.namecat = "Ryan";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 8:
                    dashboardcat.namecat = "Elesis";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 9:
                    dashboardcat.namecat = "Arme";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 10:
                    dashboardcat.namecat = "Edel";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 11:
                    dashboardcat.namecat = "Lire";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 12:
                    dashboardcat.namecat = "kopi";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 13:
                    dashboardcat.namecat = "Mari";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 14:
                    dashboardcat.namecat = "Ronan";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;

                case 15:
                    dashboardcat.namecat = "Ryan";
                    dashboardcat.imgcat = R.mipmap.ic_launcher;

                    mlistdb.add(dashboardcat);
                    break;
            }
        }
    }


}


