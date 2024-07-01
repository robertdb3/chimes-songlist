package src;

import java.io.*;
import java.util.ArrayList;

public class UserDB {
    private ArrayList<User> users;
    private static final String CSV_FILE = "data/userDB.csv";
    private static final String SONG_LIST_FOLDER = "data/songLists/";

    public UserDB() {
        this.users = new ArrayList<>();
        loadUsersFromCSV();
    }

    public User authenticate(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(User newUser) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                return false; // User already exists
            }
        }
        users.add(newUser);
        saveUsersToCSV();
        createUserSongListFile(newUser);
        return true;
    }

    private void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                User user = User.fromCSV(line);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error loading users from CSV: " + e.getMessage());
        }
    }

    private void saveUsersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (User user : users) {
                bw.write(user.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to CSV: " + e.getMessage());
        }
    }

    private void createUserSongListFile(User user) {
        String csvFilePath = SONG_LIST_FOLDER + user.getEmail() + "_songs.csv";
        try {
            File file = new File(csvFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating song list file for user " + user.getEmail() + ": " + e.getMessage());
        }
    }
}
