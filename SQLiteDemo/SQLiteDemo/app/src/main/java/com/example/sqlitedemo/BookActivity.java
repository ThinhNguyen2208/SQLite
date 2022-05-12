package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    Button btnExit , btnSelect , btnSave, btnDelete, btnUpdate;
    BookAdapter bookAdapter;
    EditText edId, edTitle, edIdAuthor;
    GridView grBook;
    ArrayList<Book> books = new ArrayList<>();
    int viTriItem = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        btnSelect = findViewById(R.id.btnSelectBook);
        btnSave = findViewById(R.id.btnSaveBook);
        btnDelete = findViewById(R.id.btnDeleteBook);
        btnUpdate = findViewById(R.id.btnUpdateBook);

        edId = findViewById(R.id.edIdBook);
        edTitle = findViewById(R.id.edTitle);
        edIdAuthor = findViewById(R.id.edIdAuthor);
        grBook = findViewById(R.id.grvBook);
        DatabaseSQLiteDemo databaseSQLiteDemo = new DatabaseSQLiteDemo(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idBook = Integer.parseInt(edId.getText().toString());
                String title = edTitle.getText().toString();
                int idAuthor = Integer.parseInt(edIdAuthor.getText().toString());
                Book book = new Book(idBook,title,idAuthor);
                if (databaseSQLiteDemo.insertBook(book)>0){
                    books.add(book);
                    bookAdapter = new BookAdapter(books);
                    grBook.setAdapter(bookAdapter);
                    Toast.makeText(getApplicationContext(),"Lưu thành công Book!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Bạn lưu không thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Book> books;
                ArrayList<String> listString = new ArrayList<>();
                books = databaseSQLiteDemo.getAllBook();
                BookAdapter bookAdapter = new BookAdapter(books);
                grBook.setAdapter(bookAdapter);
            }
        });
        grBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = (Book) adapterView.getItemAtPosition(i);
                edId.setText(book.getId() + "");
                edTitle.setText(book.getTitle()+"");
                edIdAuthor.setText(book.getId_author()+"");
                viTriItem = i;
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTriItem != -1){
                    Book book = (Book) grBook.getItemAtPosition(viTriItem);
                    if (databaseSQLiteDemo.deleteBook(book.getId()) != 0){
                        Toast.makeText(getApplicationContext(), "Xoa thanh cong author :" + book.getId(),Toast.LENGTH_SHORT).show();
                        books = databaseSQLiteDemo.getAllBook();
                        BookAdapter bookAdapter = new BookAdapter(books);
                        grBook.setAdapter(bookAdapter);
                    }else {
                        Toast.makeText(getApplicationContext(), "Xoa khong thanh cong author :",Toast.LENGTH_SHORT).show();
                    }


                }
                Toast.makeText(getApplicationContext(), "Chua chon Author can xoa!",Toast.LENGTH_SHORT).show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTriItem != -1){
                    Book book = (Book) grBook.getItemAtPosition(viTriItem);
                    int idUpdate = book.getId();
                    int idEd = Integer.parseInt(edId.getText().toString());
                    String title = edTitle.getText().toString();
                    String idAuthor = edIdAuthor.getText().toString();
                    Book bookUpdate = new Book(idUpdate,title,Integer.parseInt(idAuthor));
                    if (idEd == idUpdate){
                        if (databaseSQLiteDemo.updateBook(bookUpdate) != 0){
                            Toast.makeText(getApplicationContext(), "Cap nhat thanh cong book :" + book.getId(),Toast.LENGTH_SHORT).show();
                            books = databaseSQLiteDemo.getAllBook();
                            BookAdapter bookAdapter = new BookAdapter(books);
                            grBook.setAdapter(bookAdapter);
                        }else {
                            Toast.makeText(getApplicationContext(), "Cap nhat khong thanh cong book :",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Toast.makeText(getApplicationContext(), "Chua chon book can cap nhat!",Toast.LENGTH_SHORT).show();

            }
        });
    }
}