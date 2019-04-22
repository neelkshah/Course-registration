package bestCase;

import java.util.*;

public class altAuctionOne extends altSimpleAuction{

    private int[][] sat;
    private long aucTimer;
    private float[] value;

    public LinkedList<LinkedList<TimeTableObject>> auction(int nums, Hashtable<Integer, Course> courses, ArrayList<Student> students, PriorityQueue<TimeTableObject>[] bidQueues){
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
        return result;
    }

    public float measureStats(){
        float av = 0;
        //System.out.println("Time taken (in microseconds): " + (aucTimer/1000));
        //System.out.println("ID\t" + "#Allotted\t" + "Points used\t" + "#Not allotted\t" + "Points lost\t" + "Value");
        for(int i = 0; i < sat.length; i++) {
            av += value[i];
            //System.out.println((i + 1) + "\t\t" + sat[i][0] + "\t\t\t" + sat[i][2] + "\t\t\t" + sat[i][1] + "\t\t\t\t" + sat[i][3] + "\t\t" + value[i]);
        }
        av = av/sat.length;
        //System.out.println("Average value = " + av);
        return av;
    }
}
