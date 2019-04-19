import java.util.Scanner;

import static java.lang.System.exit;

public class altDriverOne {

    public static void main(String args[]){
        caseGenerator caseMaker = new caseGenerator();
        int bitVector[] = caseMaker.generateAllBinaryStrings(32);
        Scanner scanner = new Scanner(System.in);
        altListManip p = new altListManip();
        QueueMaker q;
        altAuctionOne auc;
        p.readCourseFile("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\coursedata.txt");
        p.readData("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\studentdata.txt", bitVector);
        q = new QueueMaker(p.students);
        auc = new altAuctionOne();
        auc.init(p.courses);
        boolean aucDone = false;
        while(true){
            System.out.print("Press 0 to exit\nPress 1 to read list of courses\nPress 2 to read list of students\nPress 3 to read section-wise seat list\nPress 4 to run auction\nPress 5 to read allotments\nPress 6 to read statistics\n");
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
                    if(aucDone){
                        break;
                    }
                    auc.auction(p.students.size(), p.courses, p.students, q.queue);
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
                case 6:
                    if(aucDone) {
                        auc.printStats();
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