public class TimeTableObject {
    public int courseCode;
    public int sectionID;
    public int bidValue;
    public boolean isCDC;

    public TimeTableObject(int course, int sectionID, int bidValue, boolean cdc){
         this.courseCode = course;
         this.sectionID = sectionID;
         this.bidValue = bidValue;
         this.isCDC = cdc;
    }

    public String toString(){
        return courseCode + " " + sectionID + " " + bidValue + " " + isCDC;
    }
}
