import java.util.Scanner;

import static java.lang.System.exit;

public class driver{

	public static void main(String args[]){
	    Scanner scanner = new Scanner(System.in);
        ListManip p = new ListManip();
        p.readCourseFile("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\coursedata.txt");
        p.readData("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\studentdata.txt");
        QueueMaker q = new QueueMaker(p.students);
        auctionOne auc = new auctionOne();
        auc.init(p.students.size(), p.courses);
        boolean aucDone = false;
        while(true){
            System.out.print("Press 0 to exit\nPress 1 to read list of courses\nPress 2 to read list of students\nPress 3 to read section-wise seat list\nPress 4 to run auction\nPress 5 to read results\n");
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
                    auc.auction(p.students.size(), p.courses, q.queue);
                    aucDone = true;
                    break;
                case 5:
                    if(aucDone) {
                        auc.printResult();
                        break;
                    }
                    else{
                        System.out.println("Please complete the auction first (Press 4)");
                        break;
                    }
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
	}
}