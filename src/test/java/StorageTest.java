
import junit.framework.TestCase;

public class StorageTest extends TestCase {
    Storage storage = new Storage();

    @Override
    protected void setUp() throws Exception {
        storage.addStudent("Вася Петров");
        storage.addStudent("Толя Сорокин");
        storage.addStudent(2, new Student(2, "Витя Пупкин"));
        storage.addStudent(3, new Student(3, "Гена Крокодилов"));
    }

    public void testAddStudent() {

        int actual = storage.getStudentList().size();
        int expected = 4;
        assertEquals(expected, actual);
    }

    public void testDeleteStudent() {
        storage.deleteStudentById(0);
        int actual = storage.getStudentList().size();
        int expected = 3;
        assertEquals(expected, actual);
    }


    public void testFindStudent() {
        Student student = storage.findStudentByName("Толя Сорокин");
        String actualName = student.getName();
        String expectedName = "Толя Сорокин";
        assertEquals(expectedName, actualName);
    }

    public void testPutGardeForStudentId() {
        storage.putGardeForStudentId(0, 5);
        int actual = storage.findStudentById(0).getGrades().get(0).getGrade();
        int expected = 5;
        assertEquals(expected, actual);
    }
    public void testUpdateStudentGrade(){
        storage.putGardeForStudentId(0, 5);
        storage.updateStudentGradeById(0, 0, 2);
        int actual = storage.findStudentById(0).getGrades().get(0).getGrade();
        int expected = 2;
        assertEquals(expected, actual);
    }

}
