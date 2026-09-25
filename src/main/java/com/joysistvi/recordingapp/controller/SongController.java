package com.joysistvi.recordingapp.controller;

public class SongController {
package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

import java.util.List;

    public class SongController {
        private final SongService songService;

        // Constructor injection
        public SongController(SongService songService) {
            this.songService = songService;
        }

        public boolean addSong(Song song) { return songService.addSong(song); }
        public List<Song> listSongs() { return songService.listSongs(); }
        public boolean updateSongTitle(int id, String newTitle) { return songService.updateSongTitle(id, newTitle); }
        public boolean deleteSong(int id) { return songService.deleteSong(id); }
    }
}
