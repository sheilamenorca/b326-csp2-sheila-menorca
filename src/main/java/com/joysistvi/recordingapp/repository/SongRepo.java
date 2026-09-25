package com.joysistvi.recordingapp.repository;


// Repo needs model
import com.joysistvi.recordingapp.model.Song;
import java.util.List;

public interface SongRepo {
    boolean addSong(Song song);
    List<Song> getAllSongs();
    boolean updateSongTitle(int id, String newTitle);
    boolean deleteSong(int id);
}