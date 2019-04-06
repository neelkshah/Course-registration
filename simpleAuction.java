import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class simpleAuction {

    public Hashtable<Integer, ArrayList<ArrayList<Integer>>> vac;

    public void init(int nums, Hashtable<Integer, Course> courses){
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

    public abstract void auction(int nums, Hashtable<Integer, Course> courses, PriorityQueue<TimeTableObject>[] bidQueues);

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
}