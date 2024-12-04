import java.util.ArrayList;

public class Album implements Comparable<Album> {
    // vs code suggested that these should be final
    private final String artist;
    private final String title;  // will use the tostring method to collect the title from the track object
    private final int year;
    private final ArrayList<Track> track_obj;
    private final Duration duration_obj;

    public Album(String artist, String title, int year){
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.track_obj = new ArrayList<Track>();
        this.duration_obj = new Duration(0, 0, 0);
    }

    public String get_artist(){
        return this.artist;
    }
    
    public String get_title(){
        return this.title;
    }

    public int get_year(){
        return this.year;
    }

    public void add_track_obj(Track track_obj){
        this.track_obj.add(track_obj);
        Duration track_duration_obj = track_obj.get_duration();
        this.duration_obj.add_sec(track_duration_obj.get_sec());
        this.duration_obj.add_min(track_duration_obj.get_min());
        this.duration_obj.add_hr(track_duration_obj.get_hr());
    }

    
    public ArrayList<Track> get_track_obj(){
        return this.track_obj;
    }

    public Duration get_duration_obj(){
        return this.duration_obj;
    }
    

    @Override
    public int compareTo(Album other){
        if(this.artist.compareTo(other.get_artist()) != 0){
            return this.artist.compareTo(other.get_artist());
        }
        else if(this.year != other.get_year()){
            return Integer.compare(this.year, other.get_year());
        }
        else{
            return this.title.compareTo(other.get_title());
        }
    }
    @Override
    public String toString(){
        return this.duration_obj.toString() + "::" + this.artist + " : " + this.title + " (" + this.year + ")";
    }
}
