package modelOne;

public class Section {
    public int courseCode;
    public int sectionID;
    public int strength;

    public Section(int courseCode, int sectionID, int strength){
        this.courseCode = courseCode;
        this.sectionID = sectionID;
        this.strength = strength;
    }

    public String toString(){
        return (sectionID + 1) + " " + strength;
    }
}