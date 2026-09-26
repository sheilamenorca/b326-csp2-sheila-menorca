package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    public boolean handleAddSong(Song song) {
        return songService.addSong(song);
    }

    public List<Song> handleViewAllSongs() {
        return songService.listSongs();
    }

    public boolean handleUpdateSongTitle(int id, String newTitle) {
        return songService.updateSongTitle(id, newTitle);
    }

    public boolean handleDeleteSong(int id) {
        return songService.deleteSong(id);
    }
}
