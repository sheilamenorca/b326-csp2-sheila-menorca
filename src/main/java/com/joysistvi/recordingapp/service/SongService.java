package com.joysistvi.recordingapp.service;



import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;
import com.joysistvi.recordingapp.repository.SongRepoImpl;

import java.util.List;

public class SongService {
    private final SongRepo songRepo;

    // Constructor injection
    public SongService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    // Example: addSong with validation
    public boolean addSong(Song song) {
        if (song.getTitle() == null || song.getTitle().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }
        return songRepo.addSong(song);
    }

    public List<Song> listSongs() {
        return songRepo.getAllSongs();
    }

    public boolean updateSongTitle(int id, String newTitle) {
        if (newTitle == null || newTitle.isEmpty()) {
            System.out.println("New title cannot be empty.");
            return false;
        }
        return songRepo.updateSongTitle(id, newTitle);
    }

    public boolean deleteSong(int id) {
        return songRepo.deleteSong(id);
    }
}