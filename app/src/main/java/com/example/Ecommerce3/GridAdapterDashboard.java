package com.example.Ecommerce3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fa.ecommerce.Model.barang;
import com.fa.ecommerce.Model.dashboardcat;
import com.fa.ecommerce.R;

import java.util.List;

public class GridAdapterDashboard extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<dashboardcat> mlist;
    private Context mcontext;
    private LayoutInflater minflater;
    private dashboardcat mdbc;
    private OnItemClickListener ocl;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        ocl = listener;
    }

    public GridAdapterDashboard(Context context, List<dashboardcat> list) {
        mlist = list;
        mcontext = context;
        minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = minflater.inflate(R.layout.item_grid_dashboard, parent, false);

        return new com.example.Ecommerce3.GridAdapterDashboard.MyHolder(v, ocl);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        com.example.Ecommerce3.GridAdapterDashboard.MyHolder myholder = (com.example.Ecommerce3.GridAdapterDashboard.MyHolder) holder;
        mdbc = mlist.get(position);

        myholder.txtcat.setText(mdbc.namecat);
        myholder.imgcat.setImageResource(mdbc.imgcat);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgcat;
        TextView txtcat;

        public MyHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            txtcat = (TextView) itemView.findViewById(R.id.txtcat);
            imgcat = (ImageButton) itemView.findViewById(R.id.imgcat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }


}
