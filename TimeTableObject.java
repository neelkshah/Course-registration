public class TimetableObject {
    public int courseCode;
    public int sectionID;
    public int bidValue;

    public TimetableObject(int courseCode, int sectionID, int bidValue){
         this.courseCode = courseCode;
         this.sectionID = sectionID;
         this.bidValue = bidValue;
    }

    public String toString(){
        return courseCode + " " + sectionID + " " + bidValue;
    }
}
