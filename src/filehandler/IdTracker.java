package filehandler;

public class IdTracker {

    public String name;
    public int lastId;

    public IdTracker(String name,int lastId)
    {
        this.name=name;
        this.lastId=lastId;
    }

    @Override
    public String toString() {
        return name+" "+lastId+"\n";
    }



}
