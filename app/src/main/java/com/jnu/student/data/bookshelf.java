package com.jnu.student.data;

import java.io.Serializable;

public class bookshelf implements Serializable {

    public bookshelf(String bookshelf){
        this.bookshelf = bookshelf;
    }

    public String getBookshelf() {return bookshelf;}
    public void setBookshelf(String bookshelf) {this.bookshelf = bookshelf;}

    private String bookshelf;

}
