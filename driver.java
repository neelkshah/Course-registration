import static java.lang.System.exit;

public class driver{

	public static void main(String args[]){
        ListManip p = new ListManip();
        p.readCourseFile("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\coursedata.txt");
        //System.out.println(p.courses.toString());
        p.readData("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\studentdata.txt");
        //p.printList();
        QueueMaker q = new QueueMaker(p.students);
        //System.out.println("\n\n\n\n\n");
        //q.printQueues();
        auctionOne auc = new auctionOne();
        auc.init(p.students.size(), p.courses);
        //auc.printSeats();
        auc.auction(p.students.size(), p.courses, q.queue);
        System.out.println(".................................................................................");
        auc.printResult();
        System.out.println(".................................................................................");
        //auc.printSeats();
	}
}