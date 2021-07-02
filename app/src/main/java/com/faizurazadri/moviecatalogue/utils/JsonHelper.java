package com.faizurazadri.moviecatalogue.utils;

import android.content.Context;

import com.faizurazadri.moviecatalogue.data.source.remote.response.MovieResponse;
import com.faizurazadri.moviecatalogue.data.source.remote.response.TvShowResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultofMethodCallIgnored")
    private String parsingFileToString(String fileNAME) {
        try {
            InputStream is = context.getAssets().open(fileNAME);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> movieResponses() {
        ArrayList<MovieResponse> movieResponseArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(parsingFileToString("movie_response.json"));
            JSONArray listArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < listArray.length(); i++) {
                JSONObject movie = listArray.getJSONObject(i);

                String id = movie.getString("id");
                String title = movie.getString("title");
                String overview = movie.getString("overview");
                String original_language = movie.getString("original_language");
                String original_title = movie.getString("original_title");
                String popularity = movie.getString("popularity");
                String release_date = movie.getString("release_date");
                String vote_average = movie.getString("vote_average");
                String vote_count = movie.getString("vote_count");
                String backdrop_path = movie.getString("backdrop_path");
                String poster_path = movie.getString("poster_path");

                MovieResponse movieResponse = new MovieResponse(backdrop_path, id, original_language, original_title, overview, popularity, poster_path, release_date, title, vote_average, vote_count);
                movieResponseArrayList.add(movieResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieResponseArrayList;
    }


    public List<TvShowResponse> tvShowResponses() {
        ArrayList<TvShowResponse> tvShowResponseArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(parsingFileToString("tv_show.json"));
            JSONArray listArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < listArray.length(); i++) {
                JSONObject tvshow = listArray.getJSONObject(i);

                String id = tvshow.getString("id");
                String name = tvshow.getString("name");
                String overview = tvshow.getString("overview");
                String original_language = tvshow.getString("original_language");
                String original_name = tvshow.getString("original_name");
                String popularity = tvshow.getString("popularity");
                String first_air_date = tvshow.getString("first_air_date");
                String vote_average = tvshow.getString("vote_average");
                String vote_count = tvshow.getString("vote_count");
                String backdrop_path = tvshow.getString("backdrop_path");
                String poster_path = tvshow.getString("poster_path");

                TvShowResponse tvShowResponse = new TvShowResponse(backdrop_path, id, original_language, original_name, overview, popularity, poster_path, first_air_date, name, vote_average, vote_count);
                tvShowResponseArrayList.add(tvShowResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tvShowResponseArrayList;
    }
}
