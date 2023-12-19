package crud;

import classes.Classroom;
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
        final Course course = new Course();
        course.setName(name);

        System.out.println("Which teacher (ID) would you like to assign?: ");
        Teacher teacher = null;
        while (teacher == null) {
            Read.showTeachers();
            int teacherId = UserInputHandler.readIntInput();
            teacher = em.find(Teacher.class, teacherId);
            if (teacher == null) {
                System.out.println("Teacher with teacherId " + teacherId + " not found.");
            }
        }
        course.setTeacher(teacher);

        System.out.println("Which classroom (ID) would you like to assign?: ");
        Classroom classroom = null;
        while (classroom == null) {
            Read.showClassrooms();
            int classroomId = UserInputHandler.readIntInput();
            classroom = em.find(Classroom.class, classroomId);
            if (classroom == null) {
                System.out.println("Classroom with classroomId " + classroomId + " not found.");
            }
        }

        course.setClassroom(classroom);
        persistObject(course);
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