package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.repository.SongRepo;
import com.joysistvi.recordingapp.repository.SongRepoImpl;
import com.joysistvi.recordingapp.service.SongService;

public class Dashboard {

    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        SongRepo songRepo = new SongRepoImpl(db);
        SongService songService = new SongService(songRepo);
        SongController songController = new SongController(songService);
        SongView songView = new SongView(songController);

        System.out.println("=== RecordingApp Main Dashboard ===");
        System.out.println("1. Song Dashboard");
        System.out.println("2. Album Dashboard");
        System.out.println("3. User Dashboard");
        System.out.println("Choose an option...");

        // dito mo tatawagin ang iba’t ibang view classes
        // e.g., SongView.run(), AlbumView.run(), UserView.run()
    }
}