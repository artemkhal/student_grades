

public class Grade {
    private int id;
    private int grade;

    public Grade(int id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return grade + "(" + id + ") ";
    }
}
