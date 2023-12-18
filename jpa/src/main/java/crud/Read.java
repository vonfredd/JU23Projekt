package crud;

import classes.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import mainclass.UserInputHandler;
import util.JPAUtil;

import java.util.List;

public class Read {
    static EntityManager em = JPAUtil.getEntityManager();

    public static void studentsFromCourse() {
        showCourse();
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("What course do you want to show?");
        int courseId = UserInputHandler.readIntInput();
        TypedQuery<StudentCourseGrade> query = em.createQuery("" +
                "SELECT scg FROM StudentCourseGrade scg " +
                "JOIN scg.course c " +
                "JOIN scg.student s " +
                "WHERE c.id = :courseId", StudentCourseGrade.class);
        query.setParameter("courseId", courseId);
        List<StudentCourseGrade> studentGrades = query.getResultList();
       for (StudentCourseGrade scg : studentGrades) {
            System.out.printf("%n%10d, %20s, %10s%n", scg.getId(), scg.getCourse(), scg.getGrade());

        }
        em.close();
    }

    public static void grades() {
        System.out.println("What name");
        String name = UserInputHandler.readStringInput();
        TypedQuery<Student> query = em.createQuery("SELECT c FROM Student c " +
                "WHERE c.firstName = :name", Student.class);
        query.setParameter("name", name);
        List<Student> grades = query.getResultList();

        em.close();
    }

    public static void showTeachers () {
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        for (Teacher t : teachers) {
            System.out.println(t.getId() + "\t" +
                    t.getFirstName() + "\t" +
                    t.getLastName());
        }
        em.close();
    }

    public static void showStudents () {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        for (Student s : students) {
            System.out.println(s.getId() + "\t" +
                    s.getFirstName() + "\t" +
                    s.getLastName());
        }
    }

    public static void showCourse(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> courses = query.getResultList();
        for (Course c : courses) {
            System.out.println(c.getId() + "\t" +
                    c.getName() + "\t" +
                    c.getTeacher() + "\t" +
                    c.getClassroom());
        }
        em.close();
    }

    public static void showGrades(){
        TypedQuery<Grade> query = em.createQuery("SELECT g FROM StudentCourseGrade scg", Grade.class);
        List<Grade> grades = query.getResultList();
        String headerGradeID = "GID: ";
        String headerGradeName = "Grade: ";
        String format = "%-20s%s";
        System.out.printf(format,headerGradeID,headerGradeName+"\n");
        for (Grade g : grades) {
            System.out.printf(format,g.getId(),g.getName()+"\n");
        }
    }
}