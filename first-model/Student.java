package modelOne;

import java.util.ArrayList;

public class Student {
    public int id;
    public int branch;
    public int balance;
    public ArrayList<TimeTableObject> timetable;

    public Student(int id, int branch, int points){
        this.id = id;
        this.branch = branch;
        this.balance = points;
        this.timetable = new ArrayList<TimeTableObject>();
    }

    public void addToTimetable(int course, int sectionNumber, int valueAssigned, boolean cdc){
        TimeTableObject ttObj = new TimeTableObject(id, course, sectionNumber, valueAssigned, cdc);
        timetable.add(ttObj);
    }

    public void printTimeTable(){
        for(int i = 0; i < timetable.size(); i++){
            System.out.println(timetable.get(i));
        }
    }

    public String toString(){
        return "ID number: " + id + "\nBranch: " + branch + "\nBalance: " + balance;
    }
}
