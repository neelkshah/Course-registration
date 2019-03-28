import java.util.ArrayList;

public class Student {
    public int id;
    public int branch;
    public int balance;
    private ArrayList<TimetableObject> timetable;

    public Student(int id, int branch, int points){
        this.id = id;
        this.branch = branch;
        this.balance = points;
        this.timetable = new ArrayList<TimetableObject>();
    }

    public void addToTimetable(int courseCode, int sectionNumber, int valueAssigned){
        TimetableObject ttObj = new TimetableObject(courseCode, sectionNumber, valueAssigned);
        timetable.add(ttObj);
    }

    public void printTimeTable(){
        for(int i = 0; i < timetable.size(); i++){
            System.out.println(timetable.get(i));
        }
    }
}
