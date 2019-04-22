package modelOne;

import java.util.ArrayList;

public class Course {
    public int courseCode;
    public int coreBranch;
    public ArrayList<Section> sectionList;
    public int cstrength;

    public Course(int courseCode, int coreBranch){
        this.courseCode = courseCode;
        this.coreBranch = coreBranch;
        this.sectionList = new ArrayList<Section>();
        this.cstrength = 0;
    }

    public void addSection(int sectionStrength){
        Section s = new Section(this.courseCode, sectionList.size(), sectionStrength);
        cstrength += sectionStrength;
        sectionList.add(s);
    }

    public void removeSection(int sectionID){
        cstrength -= sectionList.get(sectionID - 1).strength;
        sectionList.remove(sectionID - 1);
    }

    public void printSectionList(){
        for(int i = 0; i < sectionList.size(); i++){
            System.out.println(sectionList.get(i));
        }
    }

    public String toString(){
        return "Course Code: " + courseCode + "\nBranch: " + coreBranch + "\nSections:\n" + sectionList.toString();
    }
}
