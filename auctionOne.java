import java.util.*;

public class auctionOne extends simpleAuction{

    public LinkedList<LinkedList<TimeTableObject>> result;
    private ArrayList<TimeTableObject> curr;

    public auctionOne(){
        this.curr = new ArrayList<TimeTableObject>();
    }

    public void auction(int nums, Hashtable<Integer, Course> courses, PriorityQueue<TimeTableObject>[] bidQueues){
        TimeTableObject current;
        int i;
        result = new LinkedList<LinkedList<TimeTableObject>>();
        for(i = 0; i < nums; i++){
            result.add(new LinkedList<TimeTableObject>());
        }
        while(true){
            curr.clear();
            for(i = 0; i < bidQueues.length; i++){
                if(!bidQueues[i].isEmpty()) {
                    curr.add(bidQueues[i].remove());
                }
            }
            if(curr.isEmpty()){
                break;
            }
            Collections.sort(curr);
            for(i = 0; i < curr.size(); i++){
                //System.out.println("......................." + i);
                current = curr.get(i);
                //System.out.println(current.toString());
                ArrayList<ArrayList<Integer>> val = vac.get(current.courseCode);
                int seat = val.get(current.sectionID).get(1);
                if(seat > 0){
                    result.get(i).addLast(current);
                    val.get(current.sectionID).set(1, seat - 1);
                    vac.replace(current.courseCode, val);
                }
            }
        }
    }

    void printhigh(){
        for(TimeTableObject t: curr){
            System.out.println(t + "\n");
        }
    }

    void printResult(){
        for(int i = 0; i < result.size(); i++){
            System.out.println("\n....................Student " + i + "....................");
            if(!(result.get(i).size() == 0)){
                for(TimeTableObject t: result.get(i)){
                    System.out.println(t.toString());
                }
            }
            else{
                System.out.println("No allotment");
            }
        }


        /*for(LinkedList<TimeTableObject> toq: result){
            if(!(toq.peek() == null)){
                System.out.println("\n....................Student " + toq.peek().id + "....................");
            }
            else{
                System.out.println();
            }
            for(TimeTableObject t: toq){
                System.out.println(t.toString());
            }
        }*/
    }
}