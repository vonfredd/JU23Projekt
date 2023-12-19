package crud;

import classes.Grade;
import classes.Student;
import classes.StudentCourseGrade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import mainclass.Main;
import mainclass.UserInputHandler;
import util.JPAUtil;

import java.lang.reflect.Type;
import java.util.List;

import static mainclass.Main.inTransaction;


public class Update {
    static EntityManager em = JPAUtil.getEntityManager();

   /* public static void studentName() {
        System.out.println("Which student (ID) would you like to change? ");
        Read.showStudents();
        int studentID = UserInputHandler.readIntInput();
        Student student = em.find(Student.class, studentID);

        System.out.println("New name: ");
        String studentName = UserInputHandler.readStringInput();
        student.setFirstName(studentName);

        Main.inTransaction(entityManager -> {
            em.createQuery("update Student s " +
                            "set firstName=:n where id =:i")
                    .setParameter("n",studentName)
                    .setParameter("i",studentID)
                    .executeUpdate();
        });
    }*/

    public static void updateStudentName(){

        Read.showStudents();
        System.out.println("Which student (ID) would you like to change? ");
        Integer studentId = UserInputHandler.readIntInput();

        EntityManager em = JPAUtil.getEntityManager();
        Student student = em.find(Student.class,studentId);
        String name = "ooooo";
        updatePersonName(student.getId(),name);
        em.close();
    }

     public static void studentName() {
        System.out.println("Which student (ID) would you like to change? ");
        Read.showStudents();
        EntityManager em1 = JPAUtil.getEntityManager();
        int studentId = UserInputHandler.readIntInput();

        System.out.println("New first name: ");
        String studentFirstName = UserInputHandler.readStringInput();

        System.out.println("New last name: ");
        String studentLastName = UserInputHandler.readStringInput();

        EntityTransaction transaction = em1.getTransaction();
        transaction.begin();

        try {
            Student student = em1.find(Student.class, studentId);

            if (student != null) {

                student.setFirstName(studentFirstName);
                student.setLastName(studentLastName);

                em1.flush();
                transaction.commit();

                System.out.println("Student name updated successfully.");
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {em1.close();
        }
        em.close();
    }

    public static void grades() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            System.out.println("What studentId do you want to use?");
            Read.showStudents();
            int studentId = UserInputHandler.readIntInput();

            System.out.println("What grade (ID) should the student have?");
            Read.showGrades();
            int gradeId = UserInputHandler.readIntInput();

            Grade grade = em.find(Grade.class, gradeId);

            if (grade != null) {
                Query query = em.createQuery(
                        "UPDATE StudentCourseGrade scg SET scg.grade = :grade " +
                                "WHERE scg.student.id = :studentId");
                query.setParameter("grade", grade);
                query.setParameter("studentId", studentId);
                int updatedCount = query.executeUpdate();

                transaction.commit();
                System.out.println("Student grade updated successfully. Updated records: " + updatedCount);
            } else {
                System.out.println("Grade with ID " + gradeId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void gradesNew(){
        Read.showStudentCourseGrade();
        EntityManager ent = JPAUtil.getEntityManager();
        System.out.println("What ID would you like to change?");
        int scgId = UserInputHandler.readIntInput();

        Read.showGrades();
        EntityManager manager = JPAUtil.getEntityManager();

        System.out.println("What is the new grade?");
        int gradeId = UserInputHandler.readIntInput();

        StudentCourseGrade scg = manager.find(StudentCourseGrade.class, scgId);
        Grade grade = manager.find(Grade.class,gradeId);
        System.out.println("This is grade name "+grade.getName());

    }

    private static void updatePersonName(int studentId, String newName) {
        inTransaction(entityManager -> {
            Student person = entityManager.find(Student.class, studentId);

            if (person != null) {
                person.setFirstName(newName);
            } else {
                System.out.println("Person not found with id: " + studentId);
            }
        });
    }
}
