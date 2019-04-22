package bestCase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class altDriverOne {
    public static int[] bitVector = new int[32];
    public static LinkedList<LinkedList<TimeTableObject>> result;
    public static LinkedList<LinkedList<TimeTableObject>> resultMax;
    public static float value;
    public static float valueMax = 0;
    public static long nums = (long)Math.pow(2, 32);

    public static void printResult(){
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

    public static void main(String args[]){
        for(int x = 0; x < 32; x++){
            bitVector[x] = 0;
        }
        altListManip p = new altListManip();
        QueueMaker q;
        altAuctionOne auc = new altAuctionOne();
        p.readCourseFile("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\bestCase\\coursedata.txt");
        int i;
        System.out.println(nums);
        for(int count = 0; count < nums; count++) {
            System.out.println("..." + count);
            i = 0;
            while(bitVector[i] == 2) {
                bitVector[i] = 0;
                i++;
            }
            p.students.clear();
            auc.init(p.courses);
            p.readData("C:\\Users\\ABC\\IdeaProjects\\Course-Registration\\src\\bestCase\\studentdata.txt", bitVector);
            q = new QueueMaker(p.students);
            //p.printSList();
            result = auc.auction(p.students.size(), p.courses, p.students, q.queue);
            value = auc.measureStats();
            //System.out.println(value);
            if(value > valueMax){
                valueMax = value;
                resultMax = result;
            }
            bitVector[i]++;
        }
        printResult();
        System.out.println("Average global welfare = " + valueMax);
    }
}
