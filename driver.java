public class driver{

	public static void main(String args[]){
        ListManip p = new ListManip();
        p.readCourseFile("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\coursedata.txt");
        System.out.println(p.courses.toString());
        p.readData("C:\\Users\\NEEL KAUSHIK SHAH\\Desktop\\Academic Software\\trial\\src\\studentdata.txt");
        p.printList();
	}
}