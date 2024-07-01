package src;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Song> songList;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.songList = new ArrayList<>();
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
        songList.remove(song);
    }
}
