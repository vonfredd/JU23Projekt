package crud;

import classes.Course;
import classes.Student;
import classes.Teacher;
import jakarta.persistence.EntityManager;
import mainclass.UserInputHandler;
import util.JPAUtil;

import java.util.Optional;

public class Create {

    /*
    Lägg till i databasen
     */

    static EntityManager em = JPAUtil.getEntityManager();

    public static void course() {

        System.out.println("Enter new Course name: ");
        String name = UserInputHandler.readStringInput();

        System.out.println("Which Teacher (ID) would you like to assign?: ");
        Course course = new Course();
        Teacher teacher = null;
        while (teacher == null) {
            Read.showTeachers();
            int teacherId = UserInputHandler.readIntInput();
            course.setName(name);
            teacher = getTeacherById(teacherId);
            if (teacher == null) {
                System.out.println("Teacher with teacherId " + teacherId + " not found.");
            }
        }
        course.setTeacher(teacher);
    }

    private static Teacher getTeacherById(int teacherId) {
        Teacher teacher = em.find(Teacher.class, teacherId);
        return teacher;
    }

    private static void student() {
        System.out.println("Enter new student first name: ");
        String firstName = UserInputHandler.readStringInput();
        System.out.println("Enter new student last name: ");
        String lastName = UserInputHandler.readStringInput();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
    }
}
