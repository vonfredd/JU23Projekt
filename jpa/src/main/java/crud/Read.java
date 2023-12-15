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


public class Read {
    static EntityManager em = JPAUtil.getEntityManager();

    public static void studentsFromCourse() {
        showCourse();
        System.out.println("What course do you want to show?");
        int courseId = UserInputHandler.readIntInput();
        TypedQuery<StudentCourseGrade> query = em.createQuery("" +
                "SELECT scg FROM StudentCourseGrade scg " +
                "JOIN scg.course c " +
                "JOIN scg.student s " +
                "WHERE c.id = :courseId", StudentCourseGrade.class);
        query.setParameter("courseId", courseId);
        List<StudentCourseGrade> studentGrades = query.getResultList();
        studentGrades.forEach(System.out::println);
        em.close();
    }

    public static void grades() {
        System.out.println("What name");
        String name = UserInputHandler.readStringInput();
        TypedQuery<Student> query = em.createQuery("SELECT c FROM Student c WHERE c.firstName = :name", Student.class);
        query.setParameter("name", name);
        List<Student> countries = query.getResultList();
        countries.forEach(System.out::println);
        em.close();
    }

    public static void showTeachers () {
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        teachers.forEach(System.out::println);
        em.close();
    }

    public static void showStudents () {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        em.close();
        List<Student> students = query.getResultList();
        students.forEach(System.out::println);
    }

    public static void showCourse(){
        TypedQuery<Course> query = em.createQuery("SELECT s FROM Course s", Course.class);
        List<Course> courses = query.getResultList();
        courses.forEach(System.out::println);
        em.close();
    }
}
