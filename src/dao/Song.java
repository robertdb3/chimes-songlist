package src.dao;
public class Song {
    private String title;
    private String type;
    private String pitch;
    private String key;

    public Song(String title, String type, String pitch, String key) {
        this.title = title;
        this.type = type;
        this.pitch = pitch;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getPitch() {
        return pitch;
    }

    public String getKey() {
        return key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toCSV() {
        return title + "," + type + "," + pitch + "," + key;
    }

    public static Song fromCSV(String csv) {
        String[] parts = csv.split(",");
        String title = parts[0];
        String type = parts[1];
        String pitch = parts[2];
        String key = parts[3];
        return new Song(title, type, pitch, key);
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", pitch='" + pitch + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}