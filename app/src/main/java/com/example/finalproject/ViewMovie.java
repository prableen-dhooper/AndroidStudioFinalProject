package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        init();
    }

    private void init() {
        String id = getIntent().getStringExtra("id");
        int pos = Integer.valueOf(id);

        Movie mv = MainActivity.favourites.get(pos);
        TextView tvSearchTitle = findViewById(R.id.txtviewViewMovieTitle);
        TextView tvSearchYear = findViewById(R.id.txtviewViewMovieYear);
        TextView tvSearchPlot = findViewById(R.id.txtviewViewMoviePlot);
        TextView tvSearchGenre = findViewById(R.id.txtviewViewMovieGenre);
        ImageView imgSearchImage = findViewById(R.id.imgviewViewMoviePoster);
        Button btnAddFav = findViewById(R.id.btnViewMovieAddFav);

        tvSearchTitle.setText(mv.getTitle());
        tvSearchYear.setText(mv.getYear());
        tvSearchPlot.setText(mv.getPlot());
        tvSearchGenre.setText(mv.getGenres());
        btnAddFav.setText("Remove from Favourites");

        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.setFav(true);
                MainActivity.favourites.remove(pos);
                MainActivity.arrayAdapter.updateData(MainActivity.favourites);
                finish();
            }
        });

        try {
            new DownloadImageTask(imgSearchImage).execute(mv.getPosterURL());
        } catch (Exception e) {
            Log.d("RETROFIT", "onImageSet: " + e.toString());
            Log.d("RETROFIT", "onImageSet: " + e.getMessage());
        }
    }

}