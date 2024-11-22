import java.util.ArrayList;

public class AlbumCollection {
    //vs code was suggesting this to be final
    private final ArrayList<Album> collection_album_objects;

    public AlbumCollection(ArrayList<Album> object){
        this.collection_album_objects = object;
    }

    public void add_album_object(Album album_object){
        this.collection_album_objects.add(album_object);
    }

    public ArrayList<Album> get_Albums(){
        return this.collection_album_objects;
    }
}
