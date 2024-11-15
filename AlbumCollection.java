import java.util.List;

public class AlbumCollection {
    private List<Album> collection_album_objects;

    public AlbumCollection(List<Album> object){
        this.collection_album_objects = object;
    }

    public void add_album_object(Album album_object){
        this.collection_album_objects.add(album_object);
    }

    public List<Album> get_Albums(){
        return this.collection_album_objects;
    }
}
