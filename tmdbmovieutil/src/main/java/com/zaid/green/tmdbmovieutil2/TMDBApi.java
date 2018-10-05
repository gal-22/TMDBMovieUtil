package com.zaid.green.tmdbmovieutil2;

import android.content.Context;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class TMDBApi {

    private boolean isLoading;
    private String movieId = "";
    private String apiKey;

    public ArrayList<TMDBMovie> movies;

    public TMDBApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public void getPopularMovieDataRequest(final Context context, int pageNumber) {
        isLoading = true;
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        Ion.with(context)
                .load("https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&page=" + pageNumber)
                .asJsonObject() // result comes as json obj
                .setCallback(new FutureCallback<JsonObject>() { // does the request in the background, the function called when request is over (onCompleted)
                    @Override
                    public void onCompleted(Exception e, final JsonObject result) {
                        if (e != null) {
                            e.printStackTrace(); // if there is an error e. TODO Handle exception
                            isLoading = false;
                        } else {
                            if (result != null)
                                if (result.getAsJsonObject() != null && result.getAsJsonObject().get("results") != null) {
                                    for (int i = 0; i < result.getAsJsonObject().get("results").getAsJsonArray().size(); i++) {
                                        movieId = result.getAsJsonObject().getAsJsonObject().get("results").getAsJsonArray().get(i).getAsJsonObject().get("id").toString(); // TODO checck if needs a change!
                                            getMovieDataRequestFull(movieId, context);
                                    }
                                }
                        }
                        isLoading = false;

                    }

                });
    }


    private void getMovieDataRequestFull(String movieId, Context context) {
        Ion.with(context)
                .load("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&append_to_response=credits")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            e.printStackTrace(); // if there is an error e. TODO Handle exception
                        } else {
                            if (result != null) {
                                String logoPath;
                                JsonArray actorsArrJson;
                                ArrayList<TMDBActor> actorArrayList;
                                if (result.getAsJsonObject().get("backdrop_path") != null) {
                                    logoPath = result.getAsJsonObject().get("backdrop_path").toString();
                                    actorsArrJson = result.getAsJsonObject().get("credits").getAsJsonObject()
                                            .get("cast").getAsJsonArray();
                                    actorArrayList = createActorArr(actorsArrJson);
                                    renderResult(result, logoPath, actorArrayList, actorsArrJson);
                                }


                            }
                        }
                    }
                });
    }

    private ArrayList<TMDBActor> createActorArr(JsonArray actorJsonArr) {
        ArrayList<TMDBActor> actorArrayList = new ArrayList<>();
        for (int i = 0; i < actorJsonArr.size(); i++) {
            actorArrayList.add(TMDBActor.createActorFromJson(actorJsonArr.get(i).getAsJsonObject()));
        }
        return actorArrayList;
    }

    private void renderResult(JsonObject movieData, String logoPath, ArrayList<TMDBActor> actorArrayList, JsonArray actorJsonArr) {
        TMDBMovie movie;
        movie = TMDBMovie.fromJson(movieData, logoPath); // has been done just to check if the movie exists and has a name
        if (!movie.getMovieName().equals("") || movie.getMovieName() != null) {
            movie.setMovieSecondUrl(logoPath);
            movie.setActorArrayList(actorArrayList);
            movie.setActorJsonArrStr(actorJsonArr.toString());
            movies.add(movie);
        }
    }

    public ArrayList<TMDBMovie> getSavedMovies() {
        return movies;
    }


}
