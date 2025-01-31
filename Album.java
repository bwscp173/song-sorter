import java.util.ArrayList;

public class Album implements Comparable<Album> {
    private String artist;
    private String title;  // will use the tostring method to collect the title from the track object
    private int year;
    private final ArrayList<Track> track_obj;
    private Duration duration_obj;

    public Album(String artist, String title, int year){
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.track_obj = new ArrayList<>();
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

    public void set_title(String title){
        this.title = title;
    }

    public void set_year(int year){
        this.year = year;
    }

    public void set_duration_obj(Duration duration_obj){
        this.duration_obj = duration_obj;
    }

    public Integer get_hr(){
        return this.duration_obj.get_hr();
    }

    public Integer get_min(){
        return this.duration_obj.get_min();
    }
    
    public Integer get_sec(){
        return this.duration_obj.get_sec();
    }

    public void add_track_obj(Track track_obj){
        this.track_obj.add(track_obj);
        this.duration_obj.add_sec(track_obj.get_sec());
        this.duration_obj.add_min(track_obj.get_min());
        this.duration_obj.add_hr(track_obj.get_hr());
    }

    public void set_artist(String artist){
        this.artist = artist;
    }

    
    public ArrayList<Track> get_track_obj(){
        return this.track_obj;
    }

    public String get_duration(){
        return this.duration_obj.toString();
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
