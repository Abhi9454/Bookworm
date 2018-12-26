package com.bookworm.bookworm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewpagerdetails extends PagerAdapter {

    Context context;
    ArrayList<String> imagedesc;
    LayoutInflater layoutInflater;


    public viewpagerdetails(Context context, ArrayList<String> imagedesc) {
        this.context = context;
        this.imagedesc = imagedesc;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override

    public int getCount() {
        return imagedesc.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewbooklayout, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpagers);
        Picasso.get().load(imagedesc.get(position)).fit().centerInside().into(imageView);

        container.addView(itemView,0);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
