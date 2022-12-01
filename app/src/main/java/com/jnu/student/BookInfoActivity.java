package com.jnu.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookInfoActivity extends AppCompatActivity {
    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookinfo_activity);
//        initSpinnerForDropdown(); // 初始化下拉模式的列表框

        position = this.getIntent().getIntExtra("position", 0);
        String title = this.getIntent().getStringExtra("title");
        String author = this.getIntent().getStringExtra("author");
        String isbn = this.getIntent().getStringExtra("isbn");
        String bookshelf = this.getIntent().getStringExtra("bookshelf");
        String publish = this.getIntent().getStringExtra("publish");
        Double price = this.getIntent().getDoubleExtra("price", 0);

//        Toast.makeText(BookInfoActivity.this,""+title,Toast.LENGTH_SHORT).show();

        TextView editTextTitle = findViewById(R.id.book_show_title);
        TextView editTextPrice = findViewById(R.id.book_show_price);
        TextView editTextAuthor = findViewById(R.id.book_show_author);
        TextView editTextPublish = findViewById(R.id.book_show_publish);
        TextView editTextIsbn = findViewById(R.id.book_show_isbn);
//        Spinner bookshelf_selete=findViewById(R.id.bookshelf_selete);
        TextView bookshelf_selete = findViewById(R.id.book_show_bookshelf);
//        TextView book_old_title = findViewById(R.id.book_old_name);
        if (null != title) {
//            book_old_title.setText("change: " + title);
            editTextTitle.setText(title);
            editTextPrice.setText(price.toString());
            editTextAuthor.setText(author);
            editTextPublish.setText(publish);
            editTextIsbn.setText(isbn);
            bookshelf_selete.setText(bookshelf);
//            bookshelf_selete.getSelectedItem().toString();
        }
        Button button_return = findViewById(R.id.button_return);
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookInfoActivity.this.finish();
            }
        });
    }
}
