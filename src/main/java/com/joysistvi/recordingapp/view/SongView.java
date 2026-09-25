import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor injection
    public SongView(SongController songController) {
        this.songController = songController;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Song Menu ---");
            System.out.println("1. Add Song");
            System.out.println("2. List Songs");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Add Song");
                    break; // kailangan ito
                case 2:
                    System.out.println("List Songs");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void addSong() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter length (seconds): ");
        int length = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter album ID: ");
        int albumId = scanner.nextInt();
        scanner.nextLine();

        Song song = new Song(title, genre, length, albumId);
        songController.addSong(song);
    }

    private void listSongs() {
        songController.listSongs();
    }
}