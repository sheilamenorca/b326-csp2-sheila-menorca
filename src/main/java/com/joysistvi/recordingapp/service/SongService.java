package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;

import java.util.List;

public interface SongService {
    boolean addSong(Song song);
    List<Song> listSongs();
    boolean updateSongTitle(int id, String newTitle);
    boolean deleteSong(int id);
}
