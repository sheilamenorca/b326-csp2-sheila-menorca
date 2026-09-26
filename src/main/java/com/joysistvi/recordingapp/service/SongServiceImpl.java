package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;

import java.util.List;

public class SongServiceImpl implements SongService {

    private final SongRepo songRepo; // Composition

    // Constructor injection
    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public boolean addSong(Song song) {
        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }
        if (song.getAlbumId() <= 0) {
            System.out.println("Song must belong to a valid album.");
            return false;
        }
        return songRepo.addSong(song);
    }

    @Override
    public List<Song> listSongs() {
        return songRepo.getAllSongs();
    }

    @Override
    public boolean updateSongTitle(int id, String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            System.out.println("New title cannot be empty.");
            return false;
        }
        return songRepo.updateSongTitle(id, newTitle.trim());
    }

    @Override
    public boolean deleteSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }
        return songRepo.deleteSong(id);
    }
}
