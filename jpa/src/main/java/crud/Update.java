package crud;

import classes.Grade;
import classes.Student;
import classes.StudentCourseGrade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mainclass.UserInputHandler;
import util.JPAUtil;

import java.lang.reflect.Type;


public class Update {
    static EntityManager em = JPAUtil.getEntityManager();

    public static void studentName() {
        System.out.println("Which student (ID) would you like to change? ");
        Read.showStudents();
        int studentID = UserInputHandler.readIntInput();
        Student student = em.find(Student.class, studentID);

        System.out.println("New name: ");
        String studentName = UserInputHandler.readStringInput();
        student.setFirstName(studentName);

        TypedQuery <Student> query = em.createQuery("update Student s " +
                "set firstName=:n where id =:i", Student.class);
        query.setParameter("n",studentName);
        query.setParameter("i",studentID);
        query.executeUpdate();
    }

    public static void grades() {

        Read.showStudents();
        System.out.println("what studentId do you want to use?");
        EntityManager em = JPAUtil.getEntityManager();
        int studentId = UserInputHandler.readIntInput();

        Read.showGrades(); // behövs em.close() i show metod?
        System.out.println("What grade should the student have?");
        EntityManager ent = JPAUtil.getEntityManager();
        int gradeId = UserInputHandler.readIntInput();
        Grade grade = ent.find(Grade.class, gradeId);



        StudentCourseGrade student = ent.find(StudentCourseGrade.class, studentId);
        student.setGrade(grade);

    }
}
