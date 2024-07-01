package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDB userDB = new UserDB();
        SongDB songDB = new SongDB();

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

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
                    // Add Known Song
                    System.out.println("BROKEN: Enter the title of the song you want to add to your known songs:");
                    String songTitle = scanner.nextLine();
                    boolean added = currentUser.addSong(songDB, songTitle);
                    if (added) {
                        System.out.println("Song added successfully.");
                    } else {
                        System.out.println("Song not found in the universal song list.");
                    }
                    break;

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