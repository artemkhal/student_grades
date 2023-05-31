import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage {

    private int studentId;
    private Map<Integer, Student> studentList;

    public Storage() {
        studentList = new TreeMap<>();
    }

    public Map<Integer, Student> getStudentList() {
        return studentList;
    }

    public void addStudent(String studentName) {
        for (int i : studentList.keySet()) {
            if (i > studentId) studentId = i + 1;
        }
        studentList.put(studentId, new Student(studentId, studentName));
        System.out.println("Студент " + studentName + " сохранен");
        studentId++;
    }

    public void addStudent(int studentId, Student student) {
        studentList.put(studentId, student);
        System.out.println("Студент " + student.getName() + " сохранен");
    }

    public void addAllStudents(List<Student> studentList) {
        for (Student s : studentList) {
            addStudent(s.getId(), s);
        }
    }


    public void findAllStudent() {
        if (studentList.isEmpty()) {
            System.out.println("Студентов не найдено");
        } else {
            studentList.forEach((k, v) -> System.out.println(v.getName() + "(" + k + ")"));

        }
    }


    public void findAllStudentGrades() {
        if (studentList.isEmpty()) {
            System.out.println("Студентов не найдено");
        } else studentList.forEach((k, v) -> {
            System.out.println("\n" + v.getName() + "(" + k + ")");
            for (Grade i : v.getGrades()) {
                System.out.print(i.toString());
            }
        });
    }

    public void deleteStudentById(int i) {
        Student student = findStudentById(i);
        removeStudent(student);
    }

    public void deleteStudentByName(String studentName) {
        Student student = findStudentByName(studentName);
        removeStudent(student);

    }

    private void removeStudent(Student student) {
        if (student != null) {
            studentList.remove(student.getId());
            System.out.println("Студент " + student.getName() + "(" + student.getId() + ") удален");
        } else System.out.println("Студент не найден");
    }

    public Student findStudentByName(String s) {
        final Student[] findingStudent = {null};
        studentList.forEach((k, v) -> {
            if (v.getName().equalsIgnoreCase(s)) {
                System.out.println(v.getName() + "(" + k + ")");
                findingStudent[0] = v;
            }
        });
        return findingStudent[0];
    }

    public Student findStudentById(int studentId) {
        Student student = studentList.get(studentId);
        return student;
    }

    public void putGardeForStudentId(int studentId, int grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.addGrade(grade);
            studentList.put(studentId, student);
            System.out.println("Оценка " + grade + " студенту " + student.getName() + " поставлена");
        } else System.out.println("Студент не найден");
    }

    public void updateStudentGradeById(int studentId, int gradeId, int newGrade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.updateGrade(gradeId, newGrade);
            System.out.println("Оценка изменена");
        } else System.out.println("Студент не найден");
    }

    public void writeToFile(String dataPath) throws IOException {
        FileWriter writer = new FileWriter(new File(dataPath));
        studentList.forEach((k, v) -> {
            try {
                writer.write(v.getName() + "(" + v.getId() + ")\n");
                if (!v.getGrades().isEmpty()) {
                    for (Grade g : v.getGrades()) {
                        writer.write(g.toString());
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.flush();
        System.out.println("Файл записан");
    }

    public void getStudentGrades(String studentName) {
        Student student = findStudentByName(studentName);
        if (!student.getGrades().isEmpty()){
            for (Grade g : student.getGrades()){
                System.out.print(g.toString());
            }
        } else System.out.println("Оценок пока нет");
    }

    public void getStudentGrades(int i) {
        Student s = findStudentById(i);
        System.out.println(s.getName() + "(" + s.getId() + ")");
        if (!s.getGrades().isEmpty()){
            for (Grade g : s.getGrades()){
                System.out.print(g.toString());
            }
        } else System.out.println("Оценок пока нет");
    }
}
