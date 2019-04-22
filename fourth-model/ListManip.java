package modelFour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class ListManip {

    public Hashtable<Integer, Course> courses;
    public ArrayList<Student> students;

    public ListManip(){
        this.courses = new Hashtable<>();
        this.students = new ArrayList<>();
    }

    public boolean isCore(int courseCode, int studentBranch){
        Course c = courses.get(courseCode);
        if(c.coreBranch == studentBranch){
            return true;
        }
        else{
            return false;
        }
    }

    public void readCourseFile(String courseFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(courseFile));
            String strLine;
            int courseCode, branch;

            while((strLine = br.readLine()) != null){
                try{
                    String str[] = strLine.split(" ");
                    courseCode = Integer.parseInt(str[0]);
                    branch = Integer.parseInt(str[1]);
                    Course c = new Course(courseCode, branch);
                    for(int i = 2; i < str.length; i++) {
                        c.addSection(Integer.parseInt(str[i]));
                    }
                    courses.put(courseCode, c);
                    //System.out.println("Added course " + courseCode + " " + c.cstrength);
                }
                catch(NumberFormatException npe){
                    System.out.println("Error 2");
                }
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    //.....................................................................................//

    public void readData(String dataFile){
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(dataFile));
            String strLine;
            int id, branch, points, numBids;
            int courseCode, section, bid;

            while((strLine = br1.readLine()) != null){
                try{
                    String str[] = strLine.split(" ");
                    id = Integer.parseInt(str[0]);
                    branch = Integer.parseInt(str[1]);
                    points = Integer.parseInt(str[2]);
                    Student s = new Student(id, branch, points);
                    numBids = (str.length - 3)/3;
                    for(int i = 0; i < numBids; i++){
                        courseCode = Integer.parseInt(str[3*(i+1)]);
                        section = Integer.parseInt(str[3*(i+1) + 1]);
                        bid = Integer.parseInt(str[3*(i+1) + 2]);
                        s.addToTimetable(courseCode, section - 1, bid, isCore(courseCode, branch));
                    }
                    students.add(s);
                }
                catch(NumberFormatException npe){
                    System.out.println("Error 2");
                }
            }
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void printSList(){
        for(int i = 0; i < students.size(); i++){
            System.out.println(students.get(i).toString() + "\n");
        }
    }

    public void printCList(){
        Set<Integer> keys = courses.keySet();
        for(Integer key: keys){
            System.out.println(courses.get(key).toString() + "\n");
        }
    }
}