package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavMovieAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movies;

    public FavMovieAdapter(Context context, int textViewResourceId, ArrayList<Movie> movies) {
        super(context, textViewResourceId, movies);
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie mov = getItem(position);

        RecyclerView.ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_fav, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtViewMovieTitle);
        txtTitle.setText(mov.getTitle() + " - " + mov.getYear());

        return convertView;
    }

    public void updateData(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

}
