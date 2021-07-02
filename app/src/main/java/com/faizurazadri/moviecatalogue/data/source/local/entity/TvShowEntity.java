package com.faizurazadri.moviecatalogue.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshowentities")
public class TvShowEntity{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "original_language")
    private String original_language;

    @ColumnInfo(name = "original_name")
    private String original_name;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "popularity")
    private String popularity;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "first_air_date")
    private String first_air_date;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "vote_average")
    private String vote_average;

    @ColumnInfo(name = "vote_count")
    private String vote_count;

    @ColumnInfo(name = "favorite")
    private boolean favorite;

    public TvShowEntity(){

    }

    public TvShowEntity(String backdrop_path, String id, String original_language, String original_name, String overview, String popularity, String poster_path, String first_air_date, String name, String vote_average, String vote_count) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.original_language = original_language;
        this.original_name = original_name;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
        this.name = name;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

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

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
