package src;
public class Main {
    public static void main(String[] args) {
        SongDB songList = new SongDB();

        Song song1 = new Song("Mandy", "Core 80", "E", "C");

        songList.addSong(song1);

        System.out.println("List of songs:");
        songList.listSongs();
    }
}