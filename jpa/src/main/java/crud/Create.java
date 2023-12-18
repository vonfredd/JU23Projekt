package crud;

import classes.Course;
import classes.Student;
import classes.Teacher;
import jakarta.persistence.EntityManager;
import mainclass.UserInputHandler;
import util.JPAUtil;

import static mainclass.Main.inTransaction;

public class Create {

    static EntityManager em = JPAUtil.getEntityManager();

    public static void course() {
        System.out.println("Enter new course name: ");
        String name = UserInputHandler.readStringInput();

        System.out.println("Which teacher (ID) would you like to assign?: ");
        final Course course = new Course();
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
        persistObject(course);
    }

    // ToDo flytta till Teacher // Read klassen?? @Emmelie
    private static Teacher getTeacherById(int teacherId) {
        Teacher teacher = em.find(Teacher.class, teacherId);
        return teacher;
    }

    public static void student() {
        System.out.println("Enter new student first name: ");
        String firstName = UserInputHandler.readStringInput();
        System.out.println("Enter new student last name: ");
        String lastName = UserInputHandler.readStringInput();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);

        persistObject(student);
    }

    public static void teacher() {
        System.out.println("Enter new teacher first name: ");
        String firstName = UserInputHandler.readStringInput();
        System.out.println("Enter new teacher last name: ");
        String lastName = UserInputHandler.readStringInput();
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);

        persistObject(teacher);
    }

    public static void persistObject(Object o){
        inTransaction(entityManager -> entityManager.persist(o));
        em.close();
    }
}