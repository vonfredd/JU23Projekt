package crud;

import classes.Course;
import classes.Student;
import classes.StudentCourseGrade;
import classes.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mainclass.Main;
import mainclass.UserInputHandler;
import util.JPAUtil;

import java.util.List;

import static mainclass.Main.inTransaction;

public class Delete {

    static EntityManager em = JPAUtil.getEntityManager();

    public static void student() {

        System.out.println("Which student (ID) would you like to remove?: ");
        Read.showStudents();
        int studentID = UserInputHandler.readIntInput();
        Student student = em.find(Student.class, studentID);

        int studentId = student.getId();
        if (student != null) {
            TypedQuery<StudentCourseGrade> query = em.createQuery("" +
                    "SELECT scg FROM StudentCourseGrade scg " +
                    "JOIN scg.student s " +
                    "WHERE s.id = :studentId", StudentCourseGrade.class);
            query.setParameter("studentId", studentId);
            List<StudentCourseGrade> studentGrades = query.getResultList();
            for (StudentCourseGrade grade : studentGrades) {
                Main.inTransaction(entityManager -> {
                    em.remove(grade);
                    em.flush();
                });
            }
            Main.inTransaction(entityManager -> {
                em.remove(student);
                em.flush();
            });
            System.out.println("Student with studentId: " + studentID + " successfully removed.");
        } else {
            System.out.println("Teacher with studentId: " + studentID + " not found.");
        }
        em.close();
    }

    /* public static void student() {
        System.out.println("Which student (ID) would you like to remove?: ");
        Read.showStudents();
        int studentId = UserInputHandler.readIntInput();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.createQuery("DELETE FROM StudentCourseGrade scg WHERE scg.student.id = :studentId")
                        .setParameter("studentId", studentId)
                        .executeUpdate();
                em.remove(student);

                transaction.commit();
                System.out.println("Student with studentId: " + studentId + " successfully removed.");
            } else {
                System.out.println("Student with studentId: " + studentId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    } */

    public static void teacher() {
        System.out.println("Which teacher (ID) would you like to remove?: ");
        Read.showTeachers();
        int teacherId = UserInputHandler.readIntInput();
        Teacher teacher = em.find(Teacher.class, teacherId);
        if (teacher != null) {
            em.createQuery("DELETE FROM Course c WHERE c.teacher.id = :teacherId")
                    .setParameter("teacherId", teacherId)
                    .executeUpdate();
            System.out.println("Teacher with teacherId: " + teacherId + " successfully removed.");
            em.remove(teacher);
        } else {
            System.out.println("Teacher with teacherId: " + teacherId + " not found.");
        }
        em.close();
    }

    /* public static void teacher() {
        System.out.println("Which teacher (ID) would you like to remove?: ");
        Read.showTeachers();
        int teacherId = UserInputHandler.readIntInput();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Teacher teacher = em.find(Teacher.class, teacherId);
            if (teacher != null) {
                em.createQuery("UPDATE Course c SET c.teacher = null WHERE c.teacher.id = :teacherId")
                        .setParameter("teacherId", teacherId)
                        .executeUpdate();

                em.remove(teacher);

                transaction.commit();

                System.out.println("Teacher with teacherId: " + teacherId + " successfully removed.");
            } else {
                System.out.println("Teacher with teacherId: " + teacherId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }*/

    public static void course() {
        System.out.println("Which course (ID) would you like to remove?: ");
        Read.showCourses();
        int courseID = UserInputHandler.readIntInput();
        Course course = em.find(Course.class, courseID);
        if (course != null) {
            System.out.println("Course with courseId: " + courseID + " successfully removed.");
            em.remove(course);
        } else {
            System.out.println("Course with courseId: " + courseID + " not found.");
        }
        em.close();
    }

    /* public static void course() {
        System.out.println("Which course (ID) would you like to remove?: ");
        Read.showCourses();
        int courseId = UserInputHandler.readIntInput();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Course course = em.find(Course.class, courseId);
            if (course != null) {
                em.createQuery("DELETE FROM StudentCourseGrade scg WHERE scg.course.id = :courseId")
                        .setParameter("courseId", courseId)
                        .executeUpdate();
                em.remove(course);
                transaction.commit();
                System.out.println("Course with courseId: " + courseId + " successfully removed.");
            } else {
                System.out.println("Course with courseId: " + courseId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    } */

    public static void removeObject(Object o) {
        inTransaction(entityManager -> entityManager.remove(o));
        em.close();
    }
}
