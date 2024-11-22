public class Track {
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

    @Override
    public String toString(){
        return this.duration_object.toString() + " - " + this.title;
    }
}
