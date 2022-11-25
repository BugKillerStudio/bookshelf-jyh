package com.jnu.student.data;

import java.io.Serializable;

public class bookitem implements Serializable {
    public bookitem(String title, String author,String publish,String isbn,String bookshelf,Double price, int resourceId) {
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.bookshelf = bookshelf;
        this.isbn = isbn;
        this.price = price;

        this.resourceId = resourceId;
        this.position = getposition();

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }
    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getBookshelf() {
        return bookshelf;
    }
    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getposition() {
        return position;
    }
    public void setposition(int resourceId) {
        this.position = position;
    }

    private String title;
    private Double price;

    private String author;
    private String publish;
    private String bookshelf;
    private String isbn;

    private int resourceId;
    private int position;
}
