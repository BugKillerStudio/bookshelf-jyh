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

public class AddBookItem extends AppCompatActivity {
    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_shop_item);
        initSpinnerForDropdown(); // 初始化下拉模式的列表框

        position= this.getIntent().getIntExtra("position",0);
        String title=this.getIntent().getStringExtra("title");
        String author=this.getIntent().getStringExtra("title");
        String isbn=this.getIntent().getStringExtra("title");
        String bookshelf=this.getIntent().getStringExtra("title");
        String publish=this.getIntent().getStringExtra("title");

        Double price=this.getIntent().getDoubleExtra("price",0);

        EditText editTextTitle=findViewById(R.id.edittext_shop_item_title);
        EditText editTextPrice=findViewById(R.id.edittext_shop_item_price);
        TextView book_old_title=findViewById(R.id.book_old_name);
        if(null!=title)
        {
            book_old_title.setText("change: "+title);
            editTextTitle.setText(title);
            editTextPrice.setText(price.toString());
        }


        Button buttonOk=findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editTextTitle.getText().toString());
                double price=Double.parseDouble( editTextPrice.getText().toString());
                bundle.putDouble("price",price);
                bundle.putInt("position",position);

                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                AddBookItem.this.finish();
            }
        });

        Button buttoncancel=findViewById(R.id.button_cancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBookItem.this.finish();
            }
        });
    }

    private void initSpinnerForDropdown() {
        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_input_shop_item, BookshelfArray);
        // 从布局文件中获取名叫sp_dropdown的下拉框
        Spinner sp_dropdown = findViewById(R.id.bookshelf_selete);
        // 设置下拉框的标题。对话框模式才显示标题，下拉模式不显示标题
        sp_dropdown.setPrompt("请选择行星");
        sp_dropdown.setAdapter(starAdapter); // 设置下拉框的数组适配器
        sp_dropdown.setSelection(0); // 设置下拉框默认显示第一项
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp_dropdown.setOnItemSelectedListener(new MySelectedListener());
    }
    private String[] BookshelfArray = {"default"};
    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSeleted(AdapterView<?> arg0,View arg1,int arg2,long arg3){
            Toast.makeText(AddBookItem.this, "您选择的是" + BookshelfArray[arg2],
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        // 未选择时的处理方法
        public void onNothingSelected(AdapterView<?> arg0) {}
    }


}
