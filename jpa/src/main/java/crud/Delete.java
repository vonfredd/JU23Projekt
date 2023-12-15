package crud;

import classes.Course;
import classes.Student;
import classes.StudentCourseGrade;
import classes.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mainclass.UserInputHandler;
import util.JPAUtil;

import java.util.List;

public class Delete {

    static EntityManager em = JPAUtil.getEntityManager();

/*
    Delete student, both DB and from class
    Delete Teacher, both DB and from class
    Delete Course (Many to many), both db and class
 */

    /*
        Metod för att ta bort student från DB.
        Finns student i StudentCourseGrade så går den inte att ta bort utan
        att först tagits bort från StudentCourseGrade.
     */
    public static void student() {
        System.out.println("Which student (ID) would you like to remove?: ");
        Read.showStudents();
        int studentID = UserInputHandler.readIntInput();
        Student student = em.find(Student.class, studentID);

        int studentId = student.getId();
        TypedQuery<StudentCourseGrade> query = em.createQuery("" +
                "SELECT scg FROM StudentCourseGrade scg " +
                "JOIN scg.student s " +
                "WHERE s.id = :studentId", StudentCourseGrade.class);
        query.setParameter("studentId", studentId);
        List<StudentCourseGrade> studentGrades = query.getResultList();

        /*
        if(student.id == studentCourseGrade.studentId){
        em.remove(studentCourseGrade.id);
        }
            Hitta vilken id studentcourcegrade har, som eleven finns i,
            om eleven finns där, radera hela studentcourcegrade id,
            sedan student
         */
        if (student != null) {
            System.out.println("Student with studentId: " + studentID + " successfully removed.");
            em.remove(student);
        } else {
            System.out.println("Teacher with studentId: " + studentID + " not found.");
        }
        em.close();
    }

    /*
        Metod för att ta bort teacher från DB.
        Finns teacher i Course så går den inte att ta bort.
     */
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

    /*
        Metod för att ta bort teacher från DB.
        Finns teacher i Course så går den inte att ta bort.
     */
    public static void course() {
        System.out.println("Which course (ID) would you like to remove?: ");
        Read.showCourse();
        int courseID = UserInputHandler.readIntInput();
        Course course = em.find(Course.class, courseID);
        /*
            Hitta vilken id studentcourcegrade har, som eleven finns i,
            om eleven finns där, radera hela studentcourcegrade id,
            sedan student
         */
        if (course != null) {
            System.out.println("Course with courseId: " + courseID + " successfully removed.");
            em.remove(course);
        } else {
            System.out.println("Course with courseId: " + courseID + " not found.");
        }
        em.close();

    }
}
