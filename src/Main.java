package src;

import java.util.Scanner;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import src.bll.SongDB;
import src.bll.UserDB;
import src.dao.Song;
import src.dao.User;
import src.dl.IndexHandler;
import src.dl.LoginHandler;
import src.dl.DisplayLogic;

public class Main {
    final static int PORT = 8080;
    private static DisplayLogic displayLogic;
    public static void main(String[] args) throws Exception {
        UserDB userDB = new UserDB();
        SongDB songDB = new SongDB();

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        displayLogic = new DisplayLogic();

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/login", new LoginHandler(displayLogic));
        server.createContext("/", new IndexHandler(displayLogic));
        server.setExecutor(null);
        server.start();

        while (true) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Log In");
            System.out.println("2. Create Account");
            System.out.println("3. List Songs in Database");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Log In
                    System.out.println("Enter your email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();

                    currentUser = userDB.authenticate(email, password);
                    if (currentUser != null) {
                        System.out.println("User authenticated successfully: " + currentUser);
                        handleUserActions(scanner, currentUser, songDB);
                    } else {
                        System.out.println("Authentication failed.");
                    }
                    break;

                case 2:
                    // Create Account
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();

                    currentUser = new User(name, email, password);
                    if (userDB.addUser(currentUser)) {
                        System.out.println("User created successfully.");
                        handleUserActions(scanner, currentUser, songDB);
                    } else {
                        System.out.println("User already exists.");
                        currentUser = userDB.authenticate(email, password);
                    }
                    break;

                case 3:
                    // List Songs in Database
                    System.out.println("Universal Song List:");
                    songDB.listSongs();
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserActions(Scanner scanner, User currentUser, SongDB songDB) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Add Known Song");
            System.out.println("2. List Known Songs");
            System.out.println("3. Log Out");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Assuming songDB is a collection (e.g., List, Map) that contains Song objects
                    // and Song has a title property or getTitle method.
                    System.out.println("Enter the title of the song you want to add to your known songs:");
                    String songTitle = scanner.nextLine();
                    // Find the song in the songDB by title
                    Song songToAdd = null;
                    for (Song song : songDB.getSongs()) {
                        if (song.getTitle().equalsIgnoreCase(songTitle)) {
                            songToAdd = song;
                            break;
                        }
                    }
                    
                    // Add the song to the currentUser's known songs if found
                    if (songToAdd != null) {
                        boolean added = currentUser.addSong(songToAdd);
                        if (added) {
                            System.out.println("Song added successfully.");
                        } else {
                            System.out.println("Failed to add the song.");
                        }
                    } else {
                        System.out.println("Song not found in the universal song list.");
                    }

                case 2:
                    // List Known Songs
                    System.out.println("Songs known by " + currentUser.getName() + ": " + currentUser.getSongList());
                    break;

                case 3:
                    // Log Out
                    System.out.println("Logging out.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}