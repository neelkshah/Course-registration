import java.util.*;

public class prNumber {
    private Hashtable<Integer, ArrayList<ArrayList<Integer>>> vac;
    private LinkedList<LinkedList<TimeTableObject>> result;
    private int[][] sat;
    private long aucTimer;
    private float[] value;

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

    public void auction(ArrayList<Student> students){
        //System.out.println("---------------------------------" + students.size());

        long start = System.nanoTime();
        Student current;
        sat = new int[students.size()][4];
        result = new LinkedList<LinkedList<TimeTableObject>>();
        for(int i = 0; i < students.size(); i++){
            result.addLast(new LinkedList<TimeTableObject>());
        }
        int[] array = new int[students.size()];
        for(int i = 0; i < students.size(); i++){
            array[i] = i;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        for(int i = 0; i < students.size(); i++){
            current = students.get(array[i]);
            for(TimeTableObject t: current.timetable){
                ArrayList<ArrayList<Integer>> val = vac.get(t.courseCode);
                int seat = val.get(t.sectionID).get(1);
                if(seat > 0){
                    result.get(t.id - 1).addLast(t);
                    val.get(t.sectionID).set(1, seat - 1);
                    vac.replace(t.courseCode, val);
                    sat[t.id - 1][0]++;
                    sat[t.id - 1][2] += t.bidValue;
                }
                else{
                    sat[t.id - 1][1]++;
                    sat[t.id - 1][3] += t.bidValue;
                }
            }
        }
        value = new float[students.size()];
        float a, b;
        for(int i = 0; i < students.size(); i++){
            a = sat[i][2];
            b = a + sat[i][3];
            value[i] = a/b;
        }
        long end = System.nanoTime();
        aucTimer = end - start;
    }

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

    public void printStats(){
        float av = 0;
        System.out.println("Time taken (in microseconds): " + (aucTimer/1000));
        System.out.println("ID\t" + "#Allotted\t" + "Points used\t" + "#Not allotted\t" + "Points lost\t" + "Value");
        for(int i = 0; i < sat.length; i++) {
            av += value[i];
            System.out.println((i + 1) + "\t\t" + sat[i][0] + "\t\t\t" + sat[i][2] + "\t\t\t" + sat[i][1] + "\t\t\t\t" + sat[i][3] + "\t\t" + value[i]);
        }
        av = av/sat.length;
        System.out.println("Average value = " + av);
    }
}
