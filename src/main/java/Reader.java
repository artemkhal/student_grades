import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {



    public static List<Student> readFile(String filePath){
        List<Student> studentList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            studentList = read(br);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        return studentList;
    }


    private static List<Student> read(BufferedReader br) throws IOException {
        List<Student> students = new ArrayList<>();
        String line = br.readLine();
        String studentName;
        int studentId;
        while (line != null) {
            if (line.matches("[А-Я]+ [А-Я]+.+")){
                studentName = line.split("\\(")[0];
                studentId = Integer.parseInt(line.split("\\(")[1].replace(")", ""));
                Student student = new Student(studentId, studentName);
                line = br.readLine();
                if (line == null) {
                    students.add(student);
                    break;
                }
                if (line.matches("[А-Я]+ [А-Я]+.+")){
                    students.add(student);
                    continue;
                }
                if (line.matches("[[1-5]\\(\\d\\)\\s]*")){
                    String[] grades = line.split("\\)");
                    for (int i = 0; i < grades.length - 1; i++) {
                        String[] gr = grades[i].split("\\(");

                        student.getGrades().add(new Grade(Integer.parseInt(gr[1].trim()), Integer.parseInt(gr[0].trim())));
                    }
                }
                students.add(student);
            }
            line = br.readLine();
        }
        return students;
    }

}
