package com.jcs.example;
public class Book implements java.io.Serializable {
    public static final int TOTAL = 1000000;
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private Integer isbn;

    public Book() {}

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return title + " by " + author + ": " + isbn;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode() + this.author.hashCode() + this.isbn.hashCode() * 7;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Book) && ((Book)obj).getIsbn().equals(this.getIsbn())) {
            return true;
        } else {
            return false;
        }
    }
}