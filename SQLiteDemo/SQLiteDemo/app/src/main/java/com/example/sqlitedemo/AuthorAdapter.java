package com.example.sqlitedemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AuthorAdapter extends BaseAdapter {
    final ArrayList<Author> listAuthor;

    public AuthorAdapter(ArrayList<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }

    @Override
    public int getCount() {
        return listAuthor.size();
    }

    @Override
    public Object getItem(int i) {
        return listAuthor.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listAuthor.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewAuthors;
        if (view == null){
            viewAuthors = view.inflate(viewGroup.getContext(),R.layout.author_view,null );
        }else viewAuthors = view;

        Author author = (Author) getItem(i);
        ((TextView) viewAuthors.findViewById(R.id.tvId)).setText(String.format("%d",author.getId()));
        ((TextView) viewAuthors.findViewById(R.id.tvName)).setText(String.format("%s",author.getName()));
        ((TextView) viewAuthors.findViewById(R.id.tvAddress)).setText(String.format("%s",author.getAddress()));
        ((TextView) viewAuthors.findViewById(R.id.tvEmail)).setText(String.format("%s",author.getEmail()));
        return viewAuthors;
    }
}
