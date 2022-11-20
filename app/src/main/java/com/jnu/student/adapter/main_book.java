package com.jnu.student.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class main_book extends PagerAdapter {

    private List<View> mListView;

    //viewpager 构造方法
    public main_book(List<View> mListVIew){
        this.mListView = mListVIew;
    }

    //添加item
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position){
        container.addView(mListView.get(position),0);
        return mListView.get(position);
    }

    //返回数目
    @Override
    public int getCount() {
        return mListView.size();
    }

    //比较是否相等
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //销毁不用的
    @Override
    public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object){
        container.removeView(mListView.get(position));
    }
}
