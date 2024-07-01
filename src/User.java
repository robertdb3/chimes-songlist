package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Song> songList;
    private static final String SONG_LIST_FOLDER = "data/songLists/";

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.songList = new ArrayList<>();
        loadSongsFromCSV();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Song> getSongList() {
        return new ArrayList<>(songList);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addSong(Song song) {
        this.songList.add(song);
    }

    public void removeSong(Song song) {
        this.songList.remove(song);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Known Songs: " + songList.size();
    }

    public String toCSV() {
        return name + "," + email + "," + password;
    }

    public static User fromCSV(String csv) {
        String[] parts = csv.split(",");
        String name = parts[0];
        String email = parts[1];
        String password = parts[2];
        return new User(name, email, password);
    }

    private void loadSongsFromCSV() {
        String csvFilePath = SONG_LIST_FOLDER + email + "_songs.csv";
        File csvFile = new File(csvFilePath);

        // Check if the file exists
        if (!csvFile.exists()) {
            try {
                // Attempt to create a new file
                boolean fileCreated = csvFile.createNewFile();
                if (fileCreated) {
                    System.out.println("New CSV file created for user " + email);
                } else {
                    System.out.println("Failed to create new CSV file for user " + email);
                    return; // Exit the method if file creation failed
                }
            } catch (IOException e) {
                System.out.println("Error creating new CSV file for user " + email + ": " + e.getMessage());
                return; // Exit the method if an exception occurs
            }
        }

        // Continue with loading songs from the CSV
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Song song = Song.fromCSV(line);
                songList.add(song);
            }
        } catch (IOException e) {
            System.out.println("Error loading songs from CSV for user " + email + ": " + e.getMessage());
        }
    }

    private void saveSongsToCSV() {
        String csvFilePath = SONG_LIST_FOLDER + email + "_songs.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (Song song : songList) {
                bw.write(song.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving songs to CSV for user " + email + ": " + e.getMessage());
        }
    }
}
