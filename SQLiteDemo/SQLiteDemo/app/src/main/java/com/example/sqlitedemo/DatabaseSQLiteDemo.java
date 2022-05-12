package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseSQLiteDemo extends SQLiteOpenHelper {
    public DatabaseSQLiteDemo(@Nullable Context context) {
        super(context, "MYDB", null, 2);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors(id integer primary key,name text,address text, email text);");
        sqLiteDatabase.execSQL("CREATE TABLE Books(id integer primary key,title text,id_author integer not null constraint id_author references Authors(id)" +
                "ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        onCreate(sqLiteDatabase);

    }

    //thêm xóa sửa - truy vấn
    public int insertAuthor(Author author){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", author.getId() + "");
        content.put("name", author.getName() + "");
        content.put("address", author.getAddress() + "");
        content.put("email", author.getEmail() + "");
        int res = (int) database.insert("Authors", null, content);
        database.close();
        return  res;
    }
    public ArrayList<Author> getAlls(){
        ArrayList<Author> authors = new ArrayList<>();
        String sql = "Select * from Authors";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                authors.add(new Author(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
        }
        return authors;
    }
    public Author getAuthor(int authorId){
        String sql = "Select* from Authors where id="+authorId;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        Author author = null;
        if (cursor != null){

           cursor.moveToFirst();
                author= new Author(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
            cursor.close();
            database.close();
            return  author;
        }
        return  author;
    }
    public int deleteAuthor(int authorId){
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        i = db.delete("Authors", "id=?", new String[]{authorId + ""});
        db.close();
        return i;
    }
    public int updateAuthor(Author author){
        int res= 0;
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", author.getId() + "");
        content.put("name", author.getName() + "");
        content.put("address", author.getAddress() + "");
        content.put("email", author.getEmail() + "");
        res = (int) database.update("Authors",  content,"id = ?", new String[]{author.getId() + ""});
        database.close();
        return  res;
    }
    // them xoa sua for book
    public int insertBook(Book book){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", book.getId() + "");
        content.put("title", book.getTitle() + "");
        content.put("id_author", book.getId_author() + "");
        int res = (int) database.insert("Books", null, content);
        database.close();
        return  res;
    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "Select * from Books";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                books.add(new Book(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2)));
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
        }
        return books;
    }
    public Book getBook(int idBook){
        String sql = "Select* from Books where id="+idBook;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        Book book = null;
        if (cursor != null){

            cursor.moveToFirst();
            book = new Book(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2));
            cursor.close();
            database.close();
            return  book;
        }
        return  book;
    }
    public int deleteBook(int bookId){
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        i = db.delete("Books", "id=?", new String[]{bookId + ""});
        db.close();
        return i;
    }
    public int updateBook(Book book){
        int res= 0;
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", book.getId() + "");
        content.put("title", book.getTitle() + "");
        content.put("id_author", book.getId_author() + "");
        res = (int) database.update("Books",  content,"id = ?", new String[]{book.getId() + ""});
        database.close();
        return  res;
    }
}
