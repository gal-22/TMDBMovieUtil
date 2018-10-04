package com.zaid.green.tmdbmovieutil2;


import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TMDBMovie implements Serializable {
    private String movieUrl;
    private String movieSecondUrl;
    private String movieName;
    private int movieId;
    private String movieDescription;
    private double movieRating;
    private String releaseDate;
    private boolean favorite;
    private String runtime;
    private int budget;
    private int revenue;
    private ArrayList<TMDBActor> TMDBActorArrayList;
    private String actorJsonArrStr;


    public TMDBMovie(String movieUrl, String movieName, String secondImageUrl, int movieId, String movieDescription, double movieRating,
                     String releaseDate, boolean favorite, String runtime , int budget , int revenue, ArrayList<TMDBActor> TMDBActorArrayList, String actorJsonArrStr) {
        this.movieUrl = movieUrl;
        this.movieName = movieName;
        this.movieId = movieId;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
        this.movieSecondUrl = secondImageUrl;
        this.releaseDate = releaseDate;
        this.favorite = favorite;
        this.runtime = runtime;
        this.budget = budget;
        this.revenue = revenue;
        this.TMDBActorArrayList = TMDBActorArrayList;
        this.actorJsonArrStr = actorJsonArrStr;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieSecondUrl() {
        return movieSecondUrl;
    }

    public void setMovieSecondUrl(String movieSecondUrl) {
        this.movieSecondUrl = movieSecondUrl;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public TMDBMovie() {

    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }


    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public ArrayList<TMDBActor> getTMDBActorArrayList() {
        return TMDBActorArrayList;
    }

    public void setTMDBActorArrayList(ArrayList<TMDBActor> TMDBActorArrayList) {
        this.TMDBActorArrayList = TMDBActorArrayList;
    }

    public String getActorJsonArrStr() {
        return actorJsonArrStr;
    }

    public void setActorJsonArrStr(String actorJsonArrStr) {
        this.actorJsonArrStr = actorJsonArrStr;
    }

    public static TMDBMovie fromJson(JsonObject movieInfo, String extraUrl) { // TODO add the arraylist of characters
        TMDBMovie TMDBMovie = new TMDBMovie();
        TMDBMovie.movieName = fixStr(movieInfo.getAsJsonObject().get("title").toString());
        TMDBMovie.movieRating = movieInfo.getAsJsonObject().get("vote_average").getAsDouble();
        TMDBMovie.movieUrl = fixStr(movieInfo.getAsJsonObject().get("poster_path").toString());
        TMDBMovie.movieDescription = fixStr(movieInfo.getAsJsonObject().get("overview").toString());
        TMDBMovie.movieId = movieInfo.getAsJsonObject().get("id").getAsInt();
        TMDBMovie.releaseDate = fixStr(movieInfo.getAsJsonObject().get("release_date").toString());
        TMDBMovie.runtime = fixStr(movieInfo.getAsJsonObject().get("runtime").toString());
        TMDBMovie.budget = movieInfo.getAsJsonObject().get("budget").getAsInt();
        TMDBMovie.revenue = movieInfo.getAsJsonObject().get("revenue").getAsInt();
        TMDBMovie.movieSecondUrl = extraUrl;
        return TMDBMovie;
    }

    public static TMDBMovie fromJsonSearched(JsonObject movieInfo, String extraUrl) {
        TMDBMovie TMDBMovie = new TMDBMovie();
        TMDBMovie.movieName = fixStr(movieInfo.getAsJsonObject().get("title").toString());
        TMDBMovie.movieRating = movieInfo.getAsJsonObject().get("vote_average").getAsDouble();
        TMDBMovie.movieUrl = fixStr(movieInfo.getAsJsonObject().get("poster_path").toString());
        TMDBMovie.movieDescription = fixStr(movieInfo.getAsJsonObject().get("overview").toString());
        TMDBMovie.movieId = movieInfo.getAsJsonObject().get("id").getAsInt();
        TMDBMovie.releaseDate = fixStr(movieInfo.getAsJsonObject().get("release_date").toString());
        TMDBMovie.runtime = fixStr(movieInfo.getAsJsonObject().get("runtime").toString());
        TMDBMovie.budget = movieInfo.getAsJsonObject().get("budget").getAsInt();
        TMDBMovie.revenue = movieInfo.getAsJsonObject().get("revenue").getAsInt();
        TMDBMovie.movieSecondUrl = extraUrl;
        return TMDBMovie;
    }


    private static String fixStr(String str) {
        if (str.charAt(0) == '"')
            str = str.substring(1, str.length() - 1);
        return str;
    }


    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    // database functions
    public static class Properties {
        public static String id = "id";
        public static String name = "name";
        public static String favorite = "favorite";
        public static String description = "description";
        public static String movieUrl = "movieUrl";
        public static String movieSecondUrl = "movieSecondUrl";
        public static String releaseDate = "releaseDate";
        public static String movieRating = "movieRating";
        public static String runtime = "runtime";
        public static String revenue = "revenue";
        public static String budget = "budget";
        public static String actorJsonArrStr = "actorJsonArrStr";
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Properties.id, movieId);
        contentValues.put(Properties.favorite, favorite);
        contentValues.put(Properties.name, movieName);
        contentValues.put(Properties.movieUrl, movieUrl);
        contentValues.put(Properties.movieSecondUrl, movieSecondUrl);
        contentValues.put(Properties.releaseDate, releaseDate);
        contentValues.put(Properties.movieRating, movieRating);
        contentValues.put(Properties.description, movieDescription);
        contentValues.put(Properties.runtime, runtime);
        contentValues.put(Properties.budget, budget);
        contentValues.put(Properties.revenue, revenue);
        contentValues.put(Properties.actorJsonArrStr, actorJsonArrStr);
        return contentValues;
    }

    public static TMDBMovie createFromCursor(Cursor c) {
        TMDBMovie m = new TMDBMovie();
        m.movieId = c.getInt(c.getColumnIndex(Properties.id));
        m.favorite = c.getInt(c.getColumnIndex(Properties.favorite)) > 0;
        m.movieName = c.getString(c.getColumnIndex(Properties.name));
        m.movieUrl = c.getString(c.getColumnIndex(Properties.movieUrl));
        m.movieSecondUrl = c.getString(c.getColumnIndex(Properties.movieSecondUrl));
        m.releaseDate = c.getString(c.getColumnIndex(Properties.releaseDate));
        m.movieRating = c.getDouble(c.getColumnIndex(Properties.movieRating));
        m.movieDescription = c.getString(c.getColumnIndex(Properties.description));
        m.runtime = c.getString(c.getColumnIndex(Properties.runtime));
        m.revenue = c.getInt(c.getColumnIndex(Properties.revenue));
        m.budget = c.getInt(c.getColumnIndex(Properties.budget));
        m.actorJsonArrStr = c.getString(c.getColumnIndex(Properties.actorJsonArrStr));
        return m;
    }

}