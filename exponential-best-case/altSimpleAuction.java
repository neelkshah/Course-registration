package bestCase;

import java.util.*;

public abstract class altSimpleAuction {

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

    public abstract LinkedList<LinkedList<TimeTableObject>> auction(int nums, Hashtable<Integer, Course> courses, ArrayList<Student> students, PriorityQueue<TimeTableObject>[] bidQueues);

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

    void printResult(){
        for(int i = 0; i < result.size(); i++){
            System.out.println("\n....................Student " + (i + 1) + "....................");
            if(!(result.get(i).size() == 0)){
                for(TimeTableObject t: result.get(i)){
                    System.out.println(t.toString());
                }
            }
            else{
                System.out.println("No allotment");
            }
        }
    }

    public abstract float measureStats();
}
