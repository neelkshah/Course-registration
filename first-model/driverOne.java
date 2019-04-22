import sun.awt.geom.AreaOp;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class driverOne {

	public static void main(String args[]) throws IOException {
	    Scanner scanner = new Scanner(System.in);
        ListManip p = new ListManip();
        p.readCourseFile("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\coursedata.txt");
        p.readData("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\simplestudentdata.txt");
        QueueMaker q = new QueueMaker(p.students);
        auctionOne auc = new auctionOne();
        auc.init(p.courses);
        System.out.print("Press 0 to exit\nPress 1 to read list of courses\nPress 2 to read list of students\nPress 3 to read section-wise seat list\nPress 4 to run auction\n");
        while(true){
            System.out.println("Enter option:");
            switch(scanner.nextInt()){
                case 0:
                    exit(0);
                case 1:
                    p.printCList();
                    break;
                case 2:
                    p.printSList();
                    break;
                case 3:
                    auc.printSeats();
                    break;
                case 4:
                    auc.auction(p.students.size(), p.courses, p.students, q.queue);
                    auc.printResult();
                    auc.printStats();
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
	}
}