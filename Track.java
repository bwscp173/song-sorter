public class Track implements Comparable<Track>{
    private duration duration_object;
    private String title;
    
    public Track(duration duration_object, String title){
        this.duration_object = duration_object;
        this.title = title;
    }

    public String get_title(){
        return this.title;
    }
    public void set_title(String new_title){
        this.title = new_title;
    }

    public duration get_duration(){
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
