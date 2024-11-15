//this class will be used for storing the duration of either a:
//Track, Album, or Album collection
public class duration {
    private int hr;
    private int min;
    private int sec;

    public duration(int hr,int min,int sec){
        this.hr = hr;
        this.min = min;
        this.sec = sec;
    }

    public int get_hr(){
        return this.hr;
    }
    public int get_min(){
        return this.min;
    }
    public int get_sec(){
        return this.sec;
    }
}
