
import java.util.ArrayList;


//vs code wanted this line.
public class Track implements Comparable<Track>{
    private final Duration duration_object;
    private String title;
    
    public Track(Duration duration_object, String title){
        this.duration_object = duration_object;
        this.title = title;
    }

    public String get_title(){
        return this.title;
    }
    public void set_title(String new_title){
        this.title = new_title;
    }

    public ArrayList<Integer> get_duration(){
        ArrayList<Integer> time = new ArrayList<>();
        time.add(this.duration_object.get_hr());
        time.add(this.duration_object.get_min());
        time.add(this.duration_object.get_sec());
        return time;
    }

    public Integer get_hr(){
        return this.duration_object.get_hr();
    }
    public Integer get_min(){
        return this.duration_object.get_min();
    }
    public Integer get_sec(){
        return this.duration_object.get_sec();
    }

    @Override
    public int compareTo(Track other) {
        // compare this track with the other track
        // for example, compare by title
        return this.get_title().compareTo(other.get_title());
    }

    @Override
    public String toString(){
        return this.duration_object.toString() + " - " + this.title;
    }
}
