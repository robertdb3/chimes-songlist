package src;

import java.util.ArrayList;

public class SongList {
    private ArrayList<Song> songs;

    public SongList() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(String title) {
        songs.removeIf(song -> song.getTitle().equalsIgnoreCase(title));
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void listSongs() {
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
