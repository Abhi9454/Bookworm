package com.bookworm.bookworm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class viewpageradapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> imagelist;
    LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return imagelist.size();
    }

    public viewpageradapter(Context context, ArrayList<Integer> imagelist) {
        this.context = context;
        this.imagelist = imagelist;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpagerhead, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpagerimage);
        imageView.setImageResource(imagelist.get(position));

        container.addView(itemView,0);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
