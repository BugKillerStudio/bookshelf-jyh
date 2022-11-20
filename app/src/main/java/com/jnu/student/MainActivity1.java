//package com.jnu.student;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.lifecycle.Lifecycle;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.jnu.student.data.Book;
//import com.jnu.student.data.DataSaver;
//
//import java.util.ArrayList;
//
//
//public class MainActivity1 extends AppCompatActivity {
//
//    private ArrayList<Book> bookitems;
//
//    public static class PageViewFragmentAdapter extends FragmentStateAdapter {
//
//        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//            super(fragmentManager, lifecycle);
//        }          //当滑动Tab时ViewPager2获取相应Fragment
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            switch(position)
//            {
//                case 0:
//                    return BookFragment.newInstance();//不能先创建一个Fragment的list,然后再返回，要在里面创建
//            }
//            return BookFragment.newInstance();
//        }      //获得Tab对应的Fragment
//
//        @Override
//        public int getItemCount() {
//            return 1;
//        }    //Tab数目
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//
//        ViewPager2 viewPager2Main= findViewById(R.id.viewpager2_main);
//        viewPager2Main.setAdapter(new PageViewFragmentAdapter(getSupportFragmentManager(), getLifecycle()));
//
////        RecyclerView recyclerViewMain=findViewById(R.id.booklist_recycler_view);
//
//        DataSaver dataSaver = new DataSaver();
//        bookitems = dataSaver.Load(this);
//
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
////        recyclerViewMain.setLayoutManager(linearLayoutManager);
//
//    }
//}