

public class Album {
    // vs code suggested that these should be final
    private final String artist;
    private final String title;  // will use the tostring method to collect the title from the track object
    private final String album;

    public Album(String artist, String title, String album){
        this.artist = artist;
        this.title = title;
        this.album = album;
    }

    public String get_artist(){
        return this.artist;
    }
    
    public String get_title(){
        return this.title;
    }

    public String get_album(){
        return this.album;
    }

    @Override
    public String toString(){
        return this.album + " : " + this.title;
    }
}
