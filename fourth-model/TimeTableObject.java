package modelFour;

public class TimeTableObject implements Comparable<TimeTableObject> {
    public int id;
    public int courseCode;
    public int sectionID;
    public int bidValue;
    public boolean isCDC;

    public TimeTableObject(int id, int course, int sectionID, int bidValue, boolean cdc){
        //System.out.println(id + " " + course + " " + sectionID + " " + bidValue);
        this.id = id;
        this.courseCode = course;
        this.sectionID = sectionID;
        this.bidValue = bidValue;
        this.isCDC = cdc;
    }

    public String toString(){
        return id + " " + courseCode + " " + sectionID + " " + bidValue + " " + (isCDC ? "CDC" : "El");
    }

    @Override
    public int compareTo(TimeTableObject o) {
        return o.bidValue - bidValue;
    }
}