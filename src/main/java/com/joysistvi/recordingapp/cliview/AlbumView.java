package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

import java.util.List;
import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final Scanner scanner;

    public AlbumView(AlbumController albumController, Scanner scanner) {
        this.albumController = albumController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();

            choice = promptChoice();

            switch (choice) {
                case 1 -> viewAllAlbums();
                case 2 -> searchAlbum();
                case 3 -> addAlbum();
                case 4 -> updateAlbum();
                case 5 -> archiveAlbum();
                case 6 -> restoreAlbum();
                case 7 -> deleteAlbum();
                case 8 -> viewArchivedAlbums();

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
        System.out.println("\n----- Album Management -----");
        System.out.println("1. View All Albums");
        System.out.println("2. Search Album");
        System.out.println("3. Add Album");
        System.out.println("4. Update Album");
        System.out.println("5. Archive Album");
        System.out.println("6. Restore Album");
        System.out.println("7. Delete Album");
        System.out.println("8. View All Archived Albums");
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

    private void viewAllAlbums() {
        System.out.println("\n----- View All Albums -----");
        List<Album> albums = albumController.handleViewAllAlbums();
        printAlbums(albums);
    }

    private void viewArchivedAlbums() {
        System.out.println("\n----- View All Archived Albums -----");
        List<Album> albums = albumController.handleViewArchivedAlbums();
        printAlbums(albums);
    }

    private void searchAlbum() {
        System.out.println("\n----- Search Albums -----");
        System.out.print("Enter title: ");
        String keyword = scanner.nextLine();
        List<Album> albums = albumController.searchAlbum(keyword);
        printAlbums(albums);
    }

    private void addAlbum() {
        System.out.println("\n----- Add Album -----");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Artist ID: ");
        int artistId = readInt();

        System.out.print("Release Year: ");
        int releaseYear = readInt();

        Album album = new Album(title, artistId, releaseYear);

        boolean isSuccess = albumController.handleCreateAlbum(album);
        System.out.println(isSuccess ? "Album added successfully." : "Failed to add album.");

        if (isSuccess) {
            System.out.println();
            viewAllAlbums(); // read-after-write
        }
    }

    public void updateAlbum() {
        System.out.println("\n----- Update Album -----");

        // Show all albums first so the admin can see which ID to pick
        viewAllAlbums();

        System.out.print("Album ID to update: ");
        int id = readInt();

        Album current = albumController.handleGetAlbumById(id);

        if (current == null) {
            System.out.println("No album found with ID " + id + ". Please check the ID and try again.");
            return;
        }

        System.out.print("New Title [" + current.getTitle() + "] (press Enter to keep the current): ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            title = current.getTitle();
        }

        System.out.print("New Artist ID [" + current.getArtistId() + "] (press Enter to keep the current): ");
        String artistIdInput = scanner.nextLine();
        int artistId = artistIdInput.trim().isEmpty() ? current.getArtistId() : Integer.parseInt(artistIdInput.trim());

        System.out.print("New Release Year [" + current.getReleaseYear() + "] (press Enter to keep the current): ");
        String yearInput = scanner.nextLine();
        int releaseYear = yearInput.trim().isEmpty() ? current.getReleaseYear() : Integer.parseInt(yearInput.trim());

        Album album = new Album(id, title, artistId, releaseYear);

        boolean isSuccess = albumController.handleUpdateAlbum(album);
        System.out.println(isSuccess ? "Album updated successfully." : "Failed to update album.");

        if (isSuccess) {
            System.out.println();
            viewAllAlbums(); // read-after-write / refresh-after-mutation
        }
    }

    private void archiveAlbum() {
        System.out.println("\n----- Archive Album -----");
        viewAllAlbums();

        System.out.print("Album ID to archive: ");
        int id = readInt();

        boolean isSuccess = albumController.handleArchiveAlbum(id);
        System.out.println(isSuccess ? "Album archived successfully." : "Failed to archive album.");
    }

    private void restoreAlbum() {
        System.out.println("\n----- Restore Album -----");
        viewArchivedAlbums();

        System.out.print("Album ID to restore: ");
        int id = readInt();

        boolean isSuccess = albumController.handleRestoreAlbum(id);
        System.out.println(isSuccess ? "Album restored successfully." : "Failed to restore album.");
    }

    private void deleteAlbum() {
        System.out.println("\n----- Delete Album -----");
        viewAllAlbums();

        System.out.print("Album ID to delete: ");
        int id = readInt();

        boolean isSuccess = albumController.handleDeleteAlbum(id);
        System.out.println(isSuccess ? "Album deleted successfully." : "Failed to delete album.");
    }

    public void printAlbums(List<Album> albums) {
        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(27) + "+" + "-".repeat(12) + "+" + "-".repeat(8) + "+";

        System.out.println(border);
        System.out.printf("| %-4s | %-25s | %-10s | %-6s |%n", "ID", "Title", "Artist ID", "Year");
        System.out.println(border);

        for (Album album : albums) {
            System.out.printf("| %-4s | %-25s | %-10s | %-6s |%n",
                    album.getId(), album.getTitle(), album.getArtistId(), album.getReleaseYear());
        }

        System.out.println(border);
    }
}
