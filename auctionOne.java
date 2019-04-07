import java.util.*;

public class auctionOne extends simpleAuction{

    private int[][] sat;
    private long aucTimer;

    public void auction(int nums, Hashtable<Integer, Course> courses, PriorityQueue<TimeTableObject>[] bidQueues){
        ArrayList<TimeTableObject> curr = new ArrayList<TimeTableObject>();
        long start = System.nanoTime();
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
                if(seat > 0){
                    result.get(current.id - 1).addLast(current);
                    val.get(current.sectionID).set(1, seat - 1);
                    vac.replace(current.courseCode, val);
                    sat[current.id - 1][0]++;
                    sat[current.id - 1][2] += current.bidValue;
                }
                else{
                    sat[current.id - 1][1]++;
                    sat[current.id - 1][3] += current.bidValue;
                }
            }
        }
        long end = System.nanoTime();
        aucTimer = end - start;
    }

    public void printStats(){
        System.out.println("Time taken (in microseconds): " + (aucTimer/1000));
        System.out.println("ID\t" + "#Allotted\t" + "Points used\t" + "#Not allotted\t" + "Points lost");
        for(int i = 0; i < sat.length; i++) {
            System.out.println((i + 1) + "\t\t" + sat[i][0] + "\t\t\t" + sat[i][2] + "\t\t\t" + sat[i][1] + "\t\t\t\t" + sat[i][3]);
        }
    }
}