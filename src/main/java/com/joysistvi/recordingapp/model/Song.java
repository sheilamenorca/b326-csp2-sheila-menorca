package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
    private String genre;
    private int length;
    private int albumId;

    public Song() {}

    public Song(int id, String title, String genre, int length, int albumId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.albumId = albumId;
    }

    public Song(String title, String genre, int length, int albumId) {
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.albumId = albumId;
    }



    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }
}