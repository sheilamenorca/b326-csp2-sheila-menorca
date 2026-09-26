package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final Scanner scanner;

    public SongView(SongController songController, Scanner scanner) {
        this.songController = songController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();

            choice = promptChoice();

            switch (choice) {
                case 1 -> viewAllSongs();
                case 2 -> addSong();
                case 3 -> updateSongTitle();
                case 4 -> deleteSong();

                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n----- Song Management -----");
        System.out.println("1. View All Songs");
        System.out.println("2. Add Song");
        System.out.println("3. Update Song Title");
        System.out.println("4. Delete Song");
        System.out.println("0. Back");
    }

    public int promptChoice() {
        System.out.print("Choice: ");
        return readInt();
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (RuntimeException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private void viewAllSongs() {
        System.out.println("\n----- View All Songs -----");
        List<Song> songs = songController.handleViewAllSongs();
        printSongs(songs);
    }

    private void addSong() {
        System.out.println("\n----- Add Song -----");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Length (in seconds): ");
        int length = readInt();

        System.out.print("Album ID: ");
        int albumId = readInt();

        Song song = new Song(title, genre, length, albumId);

        boolean isSuccess = songController.handleAddSong(song);
        System.out.println(isSuccess ? "Song added successfully." : "Failed to add song.");

        if (isSuccess) {
            System.out.println();
            viewAllSongs(); // read-after-write
        }
    }

    private void updateSongTitle() {
        System.out.println("\n----- Update Song Title -----");

        viewAllSongs();

        System.out.print("Song ID to update: ");
        int id = readInt();

        System.out.print("New title: ");
        String newTitle = scanner.nextLine();

        boolean isSuccess = songController.handleUpdateSongTitle(id, newTitle);
        System.out.println(isSuccess ? "Song title updated successfully." : "Failed to update song title.");

        if (isSuccess) {
            System.out.println();
            viewAllSongs(); // read-after-write
        }
    }

    private void deleteSong() {
        System.out.println("\n----- Delete Song -----");

        viewAllSongs();

        System.out.print("Song ID to delete: ");
        int id = readInt();

        boolean isSuccess = songController.handleDeleteSong(id);
        System.out.println(isSuccess ? "Song deleted successfully." : "Failed to delete song.");
    }

    public void printSongs(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(22) + "+" + "-".repeat(14) + "+" + "-".repeat(9) + "+" + "-".repeat(11) + "+";

        System.out.println(border);
        System.out.printf("| %-4s | %-20s | %-12s | %-7s | %-9s |%n", "ID", "Title", "Genre", "Length", "Album ID");
        System.out.println(border);

        for (Song song : songs) {
            System.out.printf("| %-4s | %-20s | %-12s | %-7s | %-9s |%n",
                    song.getId(), song.getTitle(), song.getGenre(), song.getLength(), song.getAlbumId());
        }

        System.out.println(border);
    }
}
