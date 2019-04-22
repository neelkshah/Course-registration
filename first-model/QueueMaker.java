package modelOne;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueMaker{

    public PriorityQueue<TimeTableObject>[] queue;

    public QueueMaker(ArrayList<Student> students){
        Comparator<TimeTableObject> comp = new Comparator<TimeTableObject>() {
            @Override
            public int compare(TimeTableObject t1, TimeTableObject t2) {
                return t2.bidValue - t1.bidValue;
            }
        };
        queue = new PriorityQueue[students.size()];
        for(int i = 0; i < students.size(); i++){
            queue[i] = new PriorityQueue<TimeTableObject>(comp);
            for(int j = 0; j < students.get(i).timetable.size(); j++){
                queue[i].add(students.get(i).timetable.get(j));
            }
        }
    }

    public void printQueues(){
        for(PriorityQueue<TimeTableObject> pqueue: queue){
            for(TimeTableObject t: pqueue){
                System.out.println(t);
                System.out.println();
            }
        }
    }
}