package src;
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
}