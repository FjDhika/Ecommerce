package com.fa.ecommerce;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class slideadapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public int[] lstimage = {R.drawable.elesis, R.drawable.lire, R.drawable.arme, R.drawable.mari};
    public String[] title = {"Elesis", "Lire", "Arme", "Mari"};
    public String[] desc = {"Elesis", "Lire", "Arme", "Mari"};
    public int[] bgcolor = {Color.rgb(255, 0, 0), Color.rgb(0, 255, 0), Color.rgb(255, 0, 255), Color.rgb(0, 0, 255)};


    public slideadapter (Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.slidelinearlayout);
        ImageView imgsld = (ImageView) view.findViewById(R.id.slideimg);
        TextView txtnm = (TextView) view.findViewById(R.id.titlesld);
        TextView txtdesc = (TextView) view.findViewById(R.id.txtdesc);
        layoutslide.setBackgroundColor(bgcolor[position]);
        imgsld.setImageResource(lstimage[position]);
        txtnm.setText(title[position]);
        txtdesc.setText(desc[position]);
        container.addView(view);
        return view;
    }
        @Override
        public void destroyItem (@NonNull ViewGroup container,int position, @NonNull Object object){
            container.removeView((LinearLayout)object);
        }
    }

