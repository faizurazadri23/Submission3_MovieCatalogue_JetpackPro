package com.faizurazadri.moviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponse implements Parcelable {

    private String backdrop_path;
    private String id;
    private String original_language;
    private String original_title;
    private String overview;
    private String popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private String vote_average;
    private String vote_count;

    public MovieResponse(){

    }

    public MovieResponse(String backdrop_path, String id, String original_language, String original_title, String overview, String popularity, String poster_path, String release_date, String title, String vote_average, String vote_count) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    protected MovieResponse(Parcel in) {
        backdrop_path = in.readString();
        id = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readString();
        vote_count = in.readString();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdrop_path);
        dest.writeString(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeString(vote_average);
        dest.writeString(vote_count);
    }
}
