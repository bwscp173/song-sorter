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
        this.duration_obj.add_hr(album_object.get_hr());
        this.duration_obj.add_min(album_object.get_min());
        this.duration_obj.add_sec(album_object.get_sec());
    }

    public ArrayList<Album> get_Albums(){
        return this.collection_album_objects;
    }

    public Duration get_Duration(){
        return this.duration_obj;
    }
}
