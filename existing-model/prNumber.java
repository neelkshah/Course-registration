package existingModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            File file = new File("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\existingModel\\allotment.txt");
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

    public void printStats() throws IOException {
        float av = 0;
        String text = "Auction Statistics\n\nTime taken: " + (aucTimer/1000) + " microseconds\n\n";
        text += "ID\t" + "#Allotted\t" + "Points used\t" + "#Not allotted\t" + "Points lost\t" + "Value" + "\n";
        for(int i = 0; i < sat.length; i++) {
            av += value[i];
            text += ((i + 1) + "\t\t" + sat[i][0] + "\t\t\t" + sat[i][2] + "\t\t\t" + sat[i][1] + "\t\t\t\t" + sat[i][3] + "\t\t" + value[i] + "\n");
        }
        text += "\nGlobal welfare = " + av/sat.length + "\n";
        BufferedWriter output = null;
        try {
            File file = new File("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\existingModel\\result.txt");
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
}
