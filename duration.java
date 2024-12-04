//this class will be used for storing the duration of either a:
//Track, Album, or Album collection
public class Duration {
    private int hr;
    private int min;
    private int sec;

    public Duration(int hr,int min,int sec){
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

    public void add_hr(int to_add){
        // no error checking needed here
        this.hr += to_add;
    }
    public void add_min(int to_add){
        this.min += to_add;// % 60;
        add_hr(this.min / 60);
        this.min = this.min % 60;
    }

    public void add_sec(int to_add){
        this.sec += to_add;
        add_min(this.sec / 60);
        this.sec = this.sec % 60;
    }

    @Override
    public String toString(){
        return this.hr + ":" + this.min + ":" + this.sec;
    }
}
