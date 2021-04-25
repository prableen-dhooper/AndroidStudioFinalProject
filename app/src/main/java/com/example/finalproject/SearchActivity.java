package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import okhttp3.HttpUrl;
import retrofit2.Call;
import com.androidnetworking.AndroidNetworking;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AndroidNetworking.initialize(getApplicationContext());
        init();
    }

    // Get search term and performs search
    private void init() {
        String term = getIntent().getStringExtra("SearchTerm");
        movieAPI(term);
        // movieAsStrAPI(term);
    }

    private void movieAsStrAPI(String term) {
        String targetURL = "https://www.omdbapi.com/?i=tt3896198&apikey=debcf89e&t=" + term;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("www.omdbapi.com")
                .addQueryParameter("i", "tt3896198")
                .addQueryParameter("apikey", "debcf89e")
                .addQueryParameter("t", term)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<String> call = retrofitAPI.getMovieAsString(targetURL);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Log.d("RETROFIT", "onResponse: " + response.toString());
                Log.d("RETROFIT", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("RETROFIT", "onError: " + t.getMessage());
                // Log.d("AN", "onError: " + anError.getErrorBody());
                // Log.d("AN", "onError: " + anError.getErrorDetail());
            }
        });
    }

    // Send request to omdbapi
    private void movieAPI(String term) {
        String targetURL = "https://www.omdbapi.com/?i=tt3896198&apikey=debcf89e&t=" + term;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("www.omdbapi.com")
                .addQueryParameter("i", "tt3896198")
                .addQueryParameter("apikey", "debcf89e")
                .addQueryParameter("t", term)
                .build();

        // I used retrofit to get data from omdb
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // Prepare retrofit call to omdb
        Call<Movie> call = retrofitAPI.getMovie(targetURL);

        // Call and handle response
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, retrofit2.Response<Movie> response) {
                Log.d("RETROFIT", "onResponse: " + response.toString());
                Log.d("RETROFIT", "onResponse: " + response.body());
                final Movie mv = response.body();

                // On successfull response, fill the information to view
                TextView tvSearchTitle = findViewById(R.id.txtviewSearchMovieTitle);
                TextView tvSearchYear = findViewById(R.id.txtviewSearchMovieYear);
                TextView tvSearchPlot = findViewById(R.id.txtviewSearchMoviePlot);
                TextView tvSearchGenre = findViewById(R.id.txtviewSearchMovieGenre);
                ImageView imgSearchImage = findViewById(R.id.imgviewSearchMoviePoster);
                Button btnAddFav = findViewById(R.id.btnSearchMovieAddFav);

                tvSearchTitle.setText(mv.getTitle());
                tvSearchYear.setText(mv.getYear());
                tvSearchPlot.setText(mv.getPlot());
                tvSearchGenre.setText(mv.getGenres());
                btnAddFav.setText("Add to Favourites");

                btnAddFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mv.setFav(true);
                        MainActivity.favourites.add(mv);
                        MainActivity.arrayAdapter.updateData(MainActivity.favourites);
                        finish();
                    }
                });

                // Get image from the image url
                try {
                    new DownloadImageTask(imgSearchImage).execute(mv.getPosterURL());
                } catch (Exception e) {
                    Log.d("RETROFIT", "onImageSet: " + e.toString());
                    Log.d("RETROFIT", "onImageSet: " + e.getMessage());
                }
            }

            // Failed to get data
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("RETROFIT", "onError: " + t.getMessage());
            }
        });
    }
}