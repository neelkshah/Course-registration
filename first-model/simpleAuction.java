package modelOne;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class simpleAuction {

    public Hashtable<Integer, ArrayList<ArrayList<Integer>>> vac;
    public LinkedList<LinkedList<TimeTableObject>> result;

    public void init(Hashtable<Integer, Course> courses){
        this.vac = new Hashtable<Integer, ArrayList<ArrayList<Integer>>>();
        Set<Integer> keys = courses.keySet();
        for(Integer key: keys){
            Course temp = courses.get(key);
            ArrayList<ArrayList<Integer>> addend = new ArrayList<ArrayList<Integer>>();
            for(Section s: temp.sectionList){
                ArrayList<Integer>a = new ArrayList<Integer>();
                a.add(s.sectionID);
                a.add(s.strength);
                addend.add(a);
            }
            vac.put(temp.courseCode, addend);
        }
    }

    public abstract void auction(int nums, Hashtable<Integer, Course> courses, ArrayList<Student> students, PriorityQueue<TimeTableObject>[] bidQueues);

    public void printSeats(){
        Set<Integer> keys = vac.keySet();
        for(int key: keys){
            System.out.println("Course " + key);
            System.out.println("Section  Seats");
            ArrayList<ArrayList<Integer>> pr = vac.get(key);
            for(ArrayList<Integer> p: pr){
                System.out.println(p.get(0) + "         " + p.get(1));
            }
        }
    }

    void printResult() throws IOException {
        String text = "Course Allotments\n";
        for(int i = 0; i < result.size(); i++){
            text += ("\n....................Student " + (i + 1) + "....................\n");
            if(!(result.get(i).size() == 0)){
                for(TimeTableObject t: result.get(i)){
                    text += t.toString() + "\n";
                }
            }
            else{
                text+="No allotment\n";
            }
        }
        BufferedWriter output = null;
        try {
            File file = new File("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\modelOne\\allotment.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }

    public abstract void printStats() throws IOException;
}