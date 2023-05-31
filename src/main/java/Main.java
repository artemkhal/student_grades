
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;


public class Main {

    private static String dataPath = "/Users/artem/IdeaProjects/student_grades/src/main/resources/student_data.txt";

    private final static String help = "read_file - запись данных в файл /src/main/resources/student_data.txt\n" +
            "write_file - чтение данных из файла /src/main/resources/student_data.txt\n" +
            "add_student ИМЯ ФАМИЛИЯ - добавление студента\n" +
            "show_all_students - показывает список всех студентов\n" +
            "show_all_students_grades - показывает список оценок всех студентов\n" +
            "show_grades_for_student_name ИМЯ ФАМИЛИЯ - показывает список оценок по имени студента\n" +
            "show_grades_for_student_id ID - показывает список оценок по id студента\n" +
            "delete_student_by_name - удаляет студента по имени\n" +
            "delete_student_by_id ID - удаляет студента по id\n" +
            "put_grade_by_student_id \n" +
            "update_student_grade_by_id\n" +
            "help - посмотреть команды";

    private final static String welcome = "Программа позволяет пользователю вводить и сохранять оценки для списка учащихся.\n" +
            "Программа позволяет пользователю добавлять, удалять и обновлять оценки для каждого учащегося,\n" +
            "а также просматривать оценки для всех учащихся или для конкретного учащегося.\n" +
            "Список команд: \n" + help;


    private static Storage storage = new Storage();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(welcome);
        while (true) {
            String s = scanner.nextLine();
            String[] data = s.split(" ", 2);
            switch (data[0]) {
                case "add_student": {  // add_student ИМЯ ФАМИЛИЯ
                    try {
                        storage.addStudent(data[1].trim().toUpperCase());
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Неверно введен запрос");
                    }
                    break;
                }
                case "show_all_students": {
                    storage.findAllStudent();
                    break;
                }
                case "show_all_students_grades": {
                    storage.findAllStudentGrades();
                    break;
                }
                case "show_grades_for_student_name": {
                    storage.getStudentGrades(data[1].trim().toUpperCase());
                    break;
                }
                case "show_grades_for_student_id": {
                    try {
                        int i = Integer.parseInt(data[1]);
                    storage.getStudentGrades(i);
                    } catch (NumberFormatException e) {
                        System.out.println("Неизвестный символ");
                    }
                    break;
                }
                case "delete_student_by_name": {
                    storage.deleteStudentByName(data[1].trim().toUpperCase());
                    break;
                }
                case "delete_student_by_id": {
                    try {
                        int i = Integer.parseInt(data[1]);
                        storage.deleteStudentById(i);
                    } catch (NumberFormatException e) {
                        System.out.println("Неизвестный символ");
                    }
                    break;
                }
                case "put_grade_by_student_id": {//put_grade_by_student_id ID
                    try {
                        String[] str = data[1].split(" ");
                        int studentId = Integer.parseInt(str[0]);
                        int garde = Integer.parseInt(str[1]);
                        storage.putGardeForStudentId(studentId, garde);
                    } catch (NumberFormatException e) {
                        System.out.println("Неизвестный символ");
                    } catch (PatternSyntaxException e) {
                        System.out.println("Неизвестный символ!");
                    }
                    break;
                }
                case "update_student_grade_by_id": { //update_student_grade_by_id studentId gradeId newGrade
                    try {
                        String[] str = data[1].split(" ");
                        int studentId = Integer.parseInt(str[0]);
                        int gradeId = Integer.parseInt(str[1]);
                        int newGrade = Integer.parseInt(str[2]);
                        storage.updateStudentGradeById(studentId, gradeId, newGrade);
                    } catch (Exception e) {
                        System.out.println("Неизвестный символ");
                    }
                    break;
                }
                case "read_file": {
                    try{
                        List<Student> studentList = Reader.readFile(dataPath);
                        storage.addAllStudents(studentList);
                        System.out.println("Студенты добавлены");
                    }catch (Exception e){
                        System.out.println("Возникла ошибка при чтении файлов");
                    }
                    break;
                }
                case "write_to_file" : {
                    try {
                        storage.writeToFile(dataPath);
                    }catch (IOException e) {
                        System.out.println("Не удалось записать файл");
                    }
                    break;
                }
                case "help" : System.out.println(help);
            }
        }
    }
}