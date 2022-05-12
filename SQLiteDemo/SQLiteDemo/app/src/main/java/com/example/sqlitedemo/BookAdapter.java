package com.example.sqlitedemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    final ArrayList<Book> listBook;

    public BookAdapter(ArrayList<Book> listBook) {
        this.listBook = listBook;
    }


    @Override
    public int getCount() {
        return listBook.size();
    }

    @Override
    public Object getItem(int i) {
        return listBook.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listBook.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewBooks;
        if (view == null){
            viewBooks = view.inflate(viewGroup.getContext(), R.layout.book_view,null);
        }else viewBooks = view;

        Book book = (Book) getItem(i);
//        ((ImageView) viewBooks.findViewById(R.id.imgBook)).setImageResource(book.getId());
//        ((TextView) viewSanPhams.findViewById(R.id.tvMaSP)).setText(String.format("Seri: %d",sp.getId()));
        ((TextView) viewBooks.findViewById(R.id.tvIdBook)).setText(String.format("%d",book.getId()));
//        ((TextView) viewSanPhams.findViewById(R.id.tvSoLuong)).setText(String.format("Số lượng :%d",sp.getSoLuongSp()));
        ((TextView) viewBooks.findViewById(R.id.tvIdAuthor)).setText(String.format("%d",book.getId_author()));
        ((TextView) viewBooks.findViewById(R.id.tvTitle)).setText(String.format("%s",book.getTitle()));
        return viewBooks;
    }

}
