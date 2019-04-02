import java.util.ArrayList;

public class Course {
    public int courseCode;
    public int coreBranch;
    public ArrayList<Section> sectionList;

    public Course(int courseCode, int coreBranch){
        this.courseCode = courseCode;
        this.coreBranch = coreBranch;
        this.sectionList = new ArrayList<Section>();
    }

    public void addSection(int sectionStrength){
        Section s = new Section(this.courseCode, sectionList.size() + 1, sectionStrength);
        sectionList.add(s);
    }

    public void removeSection(int sectionID){
        sectionList.remove(sectionID + 1);
    }

    public void printSectionList(){
        for(int i = 0; i < sectionList.size(); i++){
            System.out.println(sectionList.get(i));
        }
    }

    public String toString(){
        return sectionList.toString();
    }
}
