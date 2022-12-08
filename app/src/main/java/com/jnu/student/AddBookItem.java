package com.jnu.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.jnu.student.data.DataSaver;
import com.jnu.student.data.bookshelf;

import java.util.ArrayList;

public class AddBookItem extends AppCompatActivity {
    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;
    public static int click_flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_shop_item);
//        click_flag=0;
//        initSpinnerForDropdown(); // 初始化下拉模式的列表框

        position= this.getIntent().getIntExtra("position",0);
        String title=this.getIntent().getStringExtra("title");
        String author=this.getIntent().getStringExtra("author");
        String isbn=this.getIntent().getStringExtra("isbn");
        String bookshelf=this.getIntent().getStringExtra("bookshelf");
        String publish=this.getIntent().getStringExtra("publish");

        Double price=this.getIntent().getDoubleExtra("price",0);

        EditText editTextTitle=findViewById(R.id.edittext_shop_item_title);
        EditText editTextPrice=findViewById(R.id.edittext_shop_item_price);
        EditText editTextAuthor=findViewById(R.id.edittext_shop_item_author);
        EditText editTextPublish=findViewById(R.id.edittext_shop_item_publish);
        EditText editTextIsbn=findViewById(R.id.edittext_shop_item_isbn);
//        Spinner bookshelf_selete=findViewById(R.id.bookshelf_selete);
        EditText bookshelf_selete=findViewById(R.id.edittext_shop_item_bookshelf);
        TextView book_old_title=findViewById(R.id.book_old_name);
        if(null!=title)
        {
            book_old_title.setText("change: "+title);
            editTextTitle.setText(title);
            editTextPrice.setText(price.toString());
            editTextAuthor.setText(author);
            editTextPublish.setText(publish);
            editTextIsbn.setText(isbn);
            bookshelf_selete.setText(bookshelf);
//            bookshelf_selete.getSelectedItem().toString();
        }


        Button buttonOk=findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editTextTitle.getText().toString());
                bundle.putString("isbn",editTextIsbn.getText().toString());
                bundle.putString("author",editTextAuthor.getText().toString());
                bundle.putString("publish",editTextPublish.getText().toString());
                bundle.putString("bookshelf",bookshelf_selete.getText().toString());
//                bundle.putString("bookshelf",bookshelf_selete.getSelectedItem().toString());
                double price=Double.parseDouble( editTextPrice.getText().toString());
                bundle.putDouble("price",price);
                bundle.putInt("position",position);

                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                AddBookItem.this.finish();
//                reload();
            }
        });

        Button buttoncancel=findViewById(R.id.button_cancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                click_flag=1;
                AddBookItem.this.finish();
//                reload();
            }
        });


    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
