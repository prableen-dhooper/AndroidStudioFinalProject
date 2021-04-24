package com.example.finalproject;

import com.google.gson.annotations.SerializedName;

public class Movie {

    public Movie(String title, String year, String genres, String plot, String posterURL) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.plot = plot;
        this.posterURL = posterURL;

        this.isFav = false;
    }

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    String year;

    @SerializedName("Genre")
    String genres;

    @SerializedName("Plot")
    String plot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    @SerializedName("Poster")
    String posterURL;

    public Boolean getFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }

    Boolean isFav;

    @Override
    public String toString() {
        String rt = "";

        rt += getTitle() + " - " + getYear() + " - " + getGenres() + "\n" + getPlot() + "\n" + getPosterURL() + "\n";

        return rt;
    }

}
