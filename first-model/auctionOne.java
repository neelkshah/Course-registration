package modelOne;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class auctionOne extends simpleAuction{

    private int[][] sat;
    private long aucTimer;
    private float[] value;

    public void auction(int nums, Hashtable<Integer, Course> courses, ArrayList<Student> students, PriorityQueue<TimeTableObject>[] bidQueues){
        long start = System.nanoTime();
        ArrayList<TimeTableObject> curr = new ArrayList<TimeTableObject>();
        TimeTableObject current;
        sat = new int[nums][4];
        result = new LinkedList<LinkedList<TimeTableObject>>();
        for(int i = 0; i < nums; i++){
            result.addLast(new LinkedList<TimeTableObject>());
        }
        while(true){
            curr.clear();
            for(int j = 0; j < bidQueues.length; j++){
                if(!bidQueues[j].isEmpty()) {
                    curr.add(bidQueues[j].remove());
                }
            }
            if(curr.isEmpty()){
                break;
            }
            Collections.sort(curr);
            for(int k = 0; k < curr.size(); k++){
                current = curr.get(k);
                ArrayList<ArrayList<Integer>> val = vac.get(current.courseCode);
                int seat = val.get(current.sectionID).get(1);
                if(seat > 0 && students.get(current.id - 1).balance >= current.bidValue){
                    result.get(current.id - 1).addLast(current);
                    val.get(current.sectionID).set(1, seat - 1);
                    vac.replace(current.courseCode, val);
                    sat[current.id - 1][0]++;
                    sat[current.id - 1][2] += current.bidValue;
                    students.get(current.id - 1).balance -= current.bidValue;
                }
                else{
                    sat[current.id - 1][1]++;
                    sat[current.id - 1][3] += current.bidValue;
                }
            }
        }
        value = new float[nums];
        float a, b;
        for(int i = 0; i < nums; i++){
            a = sat[i][2];
            b = a + sat[i][3];
            value[i] = a/b;
        }
        long end = System.nanoTime();
        aucTimer = end - start;
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
            File file = new File("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\modelOne\\result.txt");
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