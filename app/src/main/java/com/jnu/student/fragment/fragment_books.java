package com.jnu.student.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jnu.student.R;

public class fragment_books extends Fragment {
//    private View root;
//    private View button;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //view创建的地方
//        if(root == null)
//            root = inflater.inflate(R.layout.fragment_book,container,false);
//        final TextView textView = root.findViewById(R.id.Android_main_title_text);
//        textView.setText("onclick");
////        return super.onCreateView(inflater, container, savedInstanceState);
//        return root;
//    }
private static final String TAG = "TabFirstFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_tab_first.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_book, container, false);
        TextView tv_first = mView.findViewById(R.id.fbook);
        tv_first.setText("我是首页页面");
        return mView;
    }
}
