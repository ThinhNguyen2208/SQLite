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
import java.util.List;

public class AuthorActivity extends AppCompatActivity {

    Button btnExit , btnSelect , btnSave, btnDelete, btnUpdate;
    EditText edid, edName, edAddress, edEmail;
    GridView grvAuthor;
    ArrayList<Author> authors;
    int viTriItem = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        btnExit = findViewById(R.id.btnExit);
        btnSelect = findViewById(R.id.btnSelect);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        edid = findViewById(R.id.edId);
        edName = findViewById(R.id.edName);
        edAddress = findViewById(R.id.edAddress);
        edEmail = findViewById(R.id.edEmail);

        grvAuthor = findViewById(R.id.grvAuthor);

        DatabaseSQLiteDemo databaseSQLiteDemo = new DatabaseSQLiteDemo(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId(Integer.parseInt(edid.getText().toString()));
                author.setName(edName.getText().toString());
                author.setAddress(edAddress.getText().toString());
                author.setEmail(edEmail.getText().toString());
                if (databaseSQLiteDemo.insertAuthor(author)>0){
                    Toast.makeText(getApplicationContext(),"Lưu thành công !", Toast.LENGTH_SHORT).show();
                    authors.add(author);
                    AuthorAdapter authorAdapter = new AuthorAdapter(authors);
                    grvAuthor.setAdapter(authorAdapter);
                }else {
                    Toast.makeText(getApplicationContext(), "Bạn lưu không thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listString = new ArrayList<>();
                authors = databaseSQLiteDemo.getAlls();
                AuthorAdapter authorAdapter = new AuthorAdapter(authors);
                  grvAuthor.setAdapter(authorAdapter);
            }
        });
        grvAuthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Author author = (Author) adapterView.getItemAtPosition(i);
                edid.setText(author.getId() + "");
                edName.setText(author.getName()+"");
                edAddress.setText(author.getAddress());
                edEmail.setText(author.getEmail());
                viTriItem = i;
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTriItem != -1){
                    Author author = (Author) grvAuthor.getItemAtPosition(viTriItem);
                    if (databaseSQLiteDemo.deleteAuthor(author.getId()) != 0){
                        Toast.makeText(getApplicationContext(), "Xoa thanh cong author :" + author.getId(),Toast.LENGTH_SHORT).show();
                        authors = databaseSQLiteDemo.getAlls();
                        AuthorAdapter authorAdapter = new AuthorAdapter(authors);
                        grvAuthor.setAdapter(authorAdapter);
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
                    Author author = (Author) grvAuthor.getItemAtPosition(viTriItem);
                    int idUpdate = author.getId();
                    int idEd = Integer.parseInt(edid.getText().toString());
                    String name = edName.getText().toString();
                    String address = edAddress.getText().toString();
                    String email = edEmail.getText().toString();
                    Author authorUpdate = new Author(idUpdate,name,address,email);
                    if (idEd == idUpdate){
                        if (databaseSQLiteDemo.updateAuthor(authorUpdate) != 0){
                            Toast.makeText(getApplicationContext(), "Cap nhat thanh cong author :" + author.getId(),Toast.LENGTH_SHORT).show();
                            authors = databaseSQLiteDemo.getAlls();
                            AuthorAdapter authorAdapter = new AuthorAdapter(authors);
                            grvAuthor.setAdapter(authorAdapter);
                        }else {
                            Toast.makeText(getApplicationContext(), "Cap nhat khong thanh cong author :",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Toast.makeText(getApplicationContext(), "Chua chon Author can cap nhat!",Toast.LENGTH_SHORT).show();

            }
        });
    }
}