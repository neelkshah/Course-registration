import java.util.*;

public class auctionTwo extends simpleAuction{
    private int[][] sat;
    private long aucTimer;
    private float[] value;

    public void auction(int nums, Hashtable<Integer, Course> courses, PriorityQueue<TimeTableObject>[] bidQueues){
        long start = System.nanoTime();
        TimeTableObject current;
        sat = new int[nums][4];
        result = new LinkedList<LinkedList<TimeTableObject>>();
        for(int i = 0; i < nums; i++){
            result.addLast(new LinkedList<TimeTableObject>());
        }
        PriorityQueue<TimeTableObject> megaQueue = new PriorityQueue<>(10000, new Comparator<TimeTableObject>() {
            @Override
            public int compare(TimeTableObject o1, TimeTableObject o2) {
                return o2.bidValue - o1.bidValue;
            }
        });
        for(int i = 0; i < bidQueues.length; i++){
            while(!bidQueues[i].isEmpty()){
                megaQueue.add(bidQueues[i].poll());
            }
        }
        while(!(megaQueue.isEmpty())){
            current = megaQueue.poll();
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