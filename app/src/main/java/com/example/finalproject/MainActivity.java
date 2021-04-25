package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstFav;
    public static ArrayList<Movie> favourites;
    public static FavMovieAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        // Add on click event on the list.
        // Every click on a list item will load layout with the information of that movie
        lstFav = (ListView) findViewById(R.id.lstFav);
        lstFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewMovie.class);
                intent.putExtra("id", String.valueOf(id));
                startActivity(intent);
            }
        });

        // Add on long click event on list items
        // Every long click will delete movie from favourites
        lstFav.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                favourites.remove(position);
                arrayAdapter.updateData(favourites);
                return true;
            }
        });

        // Inititalize the favourite movie list
        favourites = new ArrayList<>();

        // Inititate array adapter for list
        arrayAdapter = new FavMovieAdapter(this, R.layout.item_fav, favourites);
        lstFav.setAdapter(arrayAdapter);
    }

    public void sendSearchRequest(View view) {
        final EditText searchTerm = (EditText) findViewById(R.id.txtSearch);
        String term = searchTerm.getText().toString();
        if(term.length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),"Please type search term",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("SearchTerm", term);
        startActivity(intent);
    }


}