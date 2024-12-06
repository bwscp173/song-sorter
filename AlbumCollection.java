import java.util.ArrayList;

public class AlbumCollection {
    //vs code was suggesting this to be final
    private final ArrayList<Album> collection_album_objects;
    private final Duration duration_obj;

    public AlbumCollection(){
        this.collection_album_objects = new ArrayList<>();
        this.duration_obj = new Duration(0, 0, 0);
    }

    public void add_album_object(Album album_object){
        this.collection_album_objects.add(album_object);

        //to account for the total duration increasing
        handle_duration(album_object);
    }

    public void handle_duration(Album album_object){
        
        Duration duration_to_add = album_object.get_duration_obj();  // so im not making constant calls to this method

        this.duration_obj.add_hr(duration_to_add.get_hr());
        this.duration_obj.add_min(duration_to_add.get_min());
        this.duration_obj.add_sec(duration_to_add.get_sec());
    }

    public ArrayList<Album> get_Albums(){
        return this.collection_album_objects;
    }

    public Duration get_Duration(){
        return this.duration_obj;
    }
}
