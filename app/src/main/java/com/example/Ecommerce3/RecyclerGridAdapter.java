package com.example.Ecommerce3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fa.ecommerce.Model.barang;
import com.fa.ecommerce.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<barang> mlist;
    private Context mcontext;
    private LayoutInflater minflater;
    private barang mbarang;

    public RecyclerGridAdapter(Context context, List<barang> list) {
        mlist = list;
        mcontext = context;
        minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = minflater.inflate(R.layout.item_grid, parent, false);

        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myholder = (MyHolder) holder;
        mbarang = mlist.get(position);

        myholder.nmbarang.setText(mbarang.pname);
        myholder.descbarang.setText(mbarang.pprice);
        Picasso.get().load(mbarang.getPimage()).into(myholder.fotobarang);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView fotobarang;
        TextView nmbarang, descbarang;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nmbarang = (TextView) itemView.findViewById(R.id.nmbarangs);
            descbarang = (TextView) itemView.findViewById(R.id.descbarangs);
            fotobarang = (ImageView) itemView.findViewById(R.id.imaged);

        }
    }
}
