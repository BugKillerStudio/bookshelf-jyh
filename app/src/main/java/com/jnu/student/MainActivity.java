package com.jnu.student;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import com.google.android.material.navigation.NavigationView;
import com.jnu.student.data.DataSaver;
import com.jnu.student.data.bookitem;
import com.jnu.student.data.bookshelf;
import com.jnu.student.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<bookitem> bookitems;
    private ArrayList<bookshelf> bookshelves;
    private MainRecycleViewAdapter mainRecycleViewAdapter;
    private ActivityMainBinding binding;
    public NavigationView mNavigationView;
    public Toolbar mToolbar;
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mActionBarDrawerToggle;
    public ImageView imageView;
    private NavigationView navView;//导航视图
    private View.OnClickListener nav_button_listener;

    private ActivityResultLauncher<Intent> addDataLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
                if(null!=result){
                    Intent intent=result.getData();
                    if(result.getResultCode()==AddBookItem.RESULT_CODE_SUCCESS)
                    {
                        Bundle bundle=intent.getExtras();
                        String title= bundle.getString("title");
                        String author= bundle.getString("author");
                        String publish= bundle.getString("publish");
                        String isbn= bundle.getString("isbn");
                        String bookshelf= bundle.getString("bookshelf");
                        double price=bundle.getDouble("price");
                        int position=bundle.getInt("position")+1;
                        bookitems.add(position, new bookitem(title,author,publish,isbn,bookshelf,price,R.drawable.book_no_name) );
                        new DataSaver().Save(this,bookitems);
                        mainRecycleViewAdapter.notifyItemInserted(position);
                    }
                }
            });

    private ActivityResultLauncher<Intent> lookbookDataLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
                if(null!=result){
                    Intent intent=result.getData();
                    if(result.getResultCode()==BookInfoActivity.RESULT_CODE_SUCCESS)
                    {
                        Bundle bundle=intent.getExtras();
                        String title= bundle.getString("title");
                        String author= bundle.getString("author");
                        String publish= bundle.getString("publish");
                        String isbn= bundle.getString("isbn");
                        String bookshelf= bundle.getString("bookshelf");
                        double price=bundle.getDouble("price");
                        int position=bundle.getInt("position")+1;

//                        mainRecycleViewAdapter.notifyItemInserted(position);
                    }
                }
            });

    private ActivityResultLauncher<Intent> updateDataLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
                if(null!=result){
                    Intent intent=result.getData();
                    if(result.getResultCode()==AddBookItem.RESULT_CODE_SUCCESS)
                    {
                        Bundle bundle=intent.getExtras();
                        String title= bundle.getString("title");
                        String author= bundle.getString("author");
                        String publish= bundle.getString("publish");
                        String isbn= bundle.getString("isbn");
                        String bookshelf= bundle.getString("bookshelf");
                        double price=bundle.getDouble("price");
                        int position=bundle.getInt("position");
                        bookitems.get(position).setTitle(title);
                        bookitems.get(position).setAuthor(author);
                        bookitems.get(position).setPublish(publish);
                        bookitems.get(position).setIsbn(isbn);
                        bookitems.get(position).setBookshelf(bookshelf);
                        bookitems.get(position).setPrice(price);
                        new DataSaver().Save(this,bookitems);
                        mainRecycleViewAdapter.notifyItemChanged(position);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain=findViewById(R.id.booklist_recycler_view);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        DataSaver dataSaver = new DataSaver();
        bookitems = dataSaver.Load(this);
//        bookshelves = dataSaver.LoadBookshelf(this);
//        bookshelves.add(new bookshelf("new_bookshelf1"));
//        bookshelves.add(new bookshelf("new_bookshelf2"));
//        bookitems = new ArrayList<>();
//        bookitems.add(new bookitem("book1 ","author1","tsinghua","1234567890","default",(double)100.0,R.drawable.book1));
//        bookitems.add(new bookitem("book1 ","author2","jnu","1234567890","default",(double)100.0,R.drawable.book2));
//        bookitems.add(new bookitem("book1 ","author3","alibaba","1234567890","default",(double)100.0,R.drawable.book3));
//        bookitems.add(new bookitem("book1 ","author4","baidu","1234567890","default",(double)100.0,R.drawable.book4));
        mainRecycleViewAdapter= new MainRecycleViewAdapter(bookitems);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);


        ///Drawerlayout start

        ///Drawerlayout end

        //悬浮按钮
        FloatingActionButton button=findViewById(R.id.addbutton);
//        FloatingActionButton button=findViewById(R.id.fab_menu_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this ,AddBookItem.class) ;
                intent.putExtra("position",bookitems.size()-1);//传递当前books的长度
                addDataLauncher.launch(intent);
            }
        });

        //DrawerLayout
//        imageView = findViewById(R.id.book_show);
//        imageView.setImageResource(R.drawable.book_no_name);
        mToolbar = (Toolbar) findViewById(R.id.second_toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.Frag_Drawerlayout);
        mToolbar.inflateMenu(R.menu.activity_main_drawer);//添加toolbar的menu

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open,R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);//setDrawerListener弃用
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id){
//                    case R.id.nav_home:
//                        mDrawerLayout.closeDrawers();
//                        break;
//                    case R.id.nav_about:
////                        mDrawerLayout.closeDrawers();
//                        Toast.makeText(MainActivity.this,"click about",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });

//        navView = findViewById(R.id.activity_main_navigationView);
//        navView.setNavigationItemSelectedListener(item -> {//导航菜单点击
//            Intent intent = new Intent();
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    intent.setClass(this, MainActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.nav_about:
////                    intent.setClass(this, AboutActivity.class);
////                    startActivity(intent);
//                    Toast.makeText(MainActivity.this,"about",Toast.LENGTH_SHORT).show();
//                    break;
//                 }
////            drawerLayout.closeDrawer(GravityCompat.START);//关闭滑动菜单
//            return true;
//        });

        //menu button start
        Button nav_home_button = findViewById(R.id.nav_home_button);
        Button nav_about_button = findViewById(R.id.nav_about_button);
        nav_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"click home",Toast.LENGTH_SHORT).show();
            }
        });
        nav_about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AboutActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"click about",Toast.LENGTH_SHORT).show();
            }
        });
        //menu button end

    }






    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case MENU_ID_ADD:
                Intent intent=new Intent(this, BookInfoActivity.class);
                intent.putExtra("position",item.getOrder());
                intent.putExtra("position",item.getOrder());
                intent.putExtra("title",bookitems.get(item.getOrder()).getTitle());
                intent.putExtra("author",bookitems.get(item.getOrder()).getAuthor());
                intent.putExtra("bookshelf",bookitems.get(item.getOrder()).getBookshelf());
                intent.putExtra("publish",bookitems.get(item.getOrder()).getPublish());
                intent.putExtra("price",bookitems.get(item.getOrder()).getPrice());
                intent.putExtra("isbn",bookitems.get(item.getOrder()).getIsbn());
                lookbookDataLauncher.launch(intent);
//                bookitems.add(item.getOrder(),new bookitem("added"+item.getOrder(),Math.random()*10,R.drawable.book_no_name));
//                mainRecycleViewAdapter.notifyItemRangeInserted(item.getOrder(), 1);
                break;
            case MENU_ID_UPDATE:
//                bookitems.get(item.getOrder()).setTitle("updated 666");
//                mainRecycleViewAdapter.notifyItemChanged(item.getOrder());
                Intent intentUpdate=new Intent(this, AddBookItem.class);
                intentUpdate.putExtra("position",item.getOrder());
                intentUpdate.putExtra("title",bookitems.get(item.getOrder()).getTitle());
                intentUpdate.putExtra("author",bookitems.get(item.getOrder()).getAuthor());
                intentUpdate.putExtra("bookshelf",bookitems.get(item.getOrder()).getBookshelf());
                intentUpdate.putExtra("publish",bookitems.get(item.getOrder()).getPublish());
                intentUpdate.putExtra("price",bookitems.get(item.getOrder()).getPrice());
                intentUpdate.putExtra("isbn",bookitems.get(item.getOrder()).getIsbn());
                updateDataLauncher.launch(intentUpdate);
                break;
            case MENU_ID_DELETE:
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("title")
                        .setMessage("are you sure to delete this item?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                bookitems.remove(item.getOrder());
                                mainRecycleViewAdapter.notifyItemRemoved(item.getOrder());
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private ArrayList<bookitem> localDataSet;
        private ArrayList<bookshelf> bookshelfDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

            private final TextView textViewTitle;
            private final TextView textViewPrice;
            private final TextView textViewAuthor;
            private final TextView textViewBookshelf;
            private final TextView textViewIsbn;
            private final TextView textViewPublish;
            private final ImageView imageViewImage;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                imageViewImage = view.findViewById(R.id.iv_icon);
                textViewTitle = view.findViewById(R.id.tv_name);
                textViewPrice = view.findViewById(R.id.tv_price);
                textViewAuthor = view.findViewById(R.id.tv_author);
                textViewBookshelf = view.findViewById(R.id.tv_bookshelf);
                textViewIsbn = view.findViewById(R.id.tv_price);
                textViewPublish = view.findViewById(R.id.tv_price);

                view.setOnCreateContextMenuListener(this);
            }

            public TextView getTextViewAuthor() {
                return textViewAuthor;
            }
            public TextView getTextViewBookshelf() {
                return textViewBookshelf;
            }
            public TextView getTextViewIsbn() {return textViewIsbn;}
            public TextView getTextViewPublish() {return textViewPublish;}
            public TextView getTextViewPrice() {
                return textViewPrice;
            }
            public TextView getTextViewTitle() {
                return textViewTitle;
            }
            public ImageView getImageViewImage() {
                return imageViewImage;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"Detail ID:"+getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"Update ID:"+getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"Delete ID:"+getAdapterPosition());
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<bookitem> dataSet) {
            localDataSet = dataSet;
        }

        public void BookshelfAdapter(ArrayList<bookshelf> bookshelves) {
            bookshelfDataSet = bookshelves;
        }

        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_book, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextViewTitle().setText("Name: "+localDataSet.get(position).getTitle());
            viewHolder.getTextViewAuthor().setText("Author: "+localDataSet.get(position).getAuthor());
            viewHolder.getTextViewBookshelf().setText("Bookshelf: "+localDataSet.get(position).getBookshelf());
            viewHolder.getTextViewPrice().setText("Price: "+localDataSet.get(position).getPrice().toString());
            viewHolder.getTextViewIsbn().setText("Isbn"+localDataSet.get(position).getIsbn());
            viewHolder.getTextViewPublish().setText("Publish"+localDataSet.get(position).getPublish());
            viewHolder.getTextViewPrice().setText("Price"+localDataSet.get(position).getPrice());
//            viewHolder.getImageViewImage().setImageResource(localDataSet.get(position).getResourceId());
            viewHolder.getImageViewImage().setImageResource(localDataSet.get(position).getResourceId());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}