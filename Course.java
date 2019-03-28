import java.util.ArrayList;

public class Course {
    public int courseCode;
    public int coreBranch;
    public boolean isCDC;
    public ArrayList<Section> sectionList;

    public Course(int courseCode, int coreBranch, boolean isCDC){
        this.courseCode = courseCode;
        this.coreBranch = coreBranch;
        this.isCDC = isCDC;
        this.sectionList = new ArrayList<Section>();
    }

    public void addSection(int sectionID, int sectionStrength){
        Section s = new Section(this.courseCode, sectionID, sectionStrength);
        sectionList.add(s);
    }
}
