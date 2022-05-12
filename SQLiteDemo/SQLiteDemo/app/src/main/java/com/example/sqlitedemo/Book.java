package com.example.sqlitedemo;

public class Book {
    private int id;
    private String title;
    private int id_author;

    public Book(int id, String title, int id_author) {
        this.id = id;
        this.title = title;
        this.id_author = id_author;
    }
    public Book() {
        this.id = 0;
        this.title = null;
        this.id_author = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", id_author=" + id_author +
                '}';
    }
}
