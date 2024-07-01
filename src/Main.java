package src;
public class Main {
    public static void main(String[] args) {
        SongDB songList = new SongDB();

        System.out.println("Welcome to the Georgetown Chimes Song List Portal!");
        System.out.println("Please log in below:");

        


        System.out.println("List of songs:");
        songList.listSongs();
    }
}