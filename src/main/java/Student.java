import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;

    private String name;
    private List<Grade> grades;

    private int gradeId;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        grades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(int grade){
        grades.add(new Grade(gradeId, grade));
        gradeId++;
    }

    public boolean deleteGrade(int id){
        for (Grade g : grades){
            if (g.getId() == id){
                grades.remove(g);
                return true;
            }
        }
        return false;
    }
    public boolean updateGrade(int gradeId, int newGrade){
        for (Grade g : grades){
            if (g.getId() == gradeId){
                g.setGrade(newGrade);
                return true;
            }
        }
        return false;
    }

}
