public class Track implements Comparable<Track>{
    private Duration duration_object;
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

    public Duration get_duration(){
        return this.duration_object;
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
