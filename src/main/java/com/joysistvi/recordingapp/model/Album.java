package com.joysistvi.recordingapp.model;

public class Album {

    private int id;
    private String title;
    private int artistId;
    private int releaseYear;

    public Album(String title, int artistId, int releaseYear) {
        this.title = title;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public Album(int id, String title, int artistId, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
