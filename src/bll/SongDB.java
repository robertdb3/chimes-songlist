package src.bll;

import java.io.*;
import java.util.ArrayList;

import src.dao.Song;

public class SongDB {
    private ArrayList<Song> songs;
    private static final String CSV_FILE = "data/songDB.csv";

    public SongDB() {
        this.songs = new ArrayList<>();
        loadSongsFromCSV();
    }

    public Song getSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void listSongs() {
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    private void loadSongsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Song song = Song.fromCSV(line);
                this.songs.add(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSongsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Song song : songs) {
                writer.write(song.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
