package crud;

import classes.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import mainclass.UserInputHandler;
import util.JPAUtil;

import java.util.List;

public class Read {
    //static EntityManager em = JPAUtil.getEntityManager();

    public static void studentsFromCourse() {
        showCourses();
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
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("What name");
        String name = UserInputHandler.readStringInput();
        TypedQuery<Student> query = em.createQuery("SELECT c FROM Student c " +
                "WHERE c.firstName = :name", Student.class);
        query.setParameter("name", name);
        List<Student> grades = query.getResultList();

        em.close();
    }

    public static void showTeachers() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        for (Teacher t : teachers) {
            System.out.println(t.getId() + "\t" +
                    t.getFirstName() + "\t" +
                    t.getLastName());
        }
        em.close();
    }

    /* public static void showTeachers () {
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        for (Teacher t : teachers) {
            System.out.println(t.getId() + "\t" +
                    t.getFirstName() + "\t" +
                    t.getLastName());
        }
    } */

    public static void showStudents() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        for (Student s : students) {
            System.out.println(s.getId() + "\t" +
                    s.getFirstName() + "\t" +
                    s.getLastName());
        }
        em.close();
    }


    public static void showCourses() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT c, t, cl " +
                            "FROM Course c " +
                            "LEFT JOIN c.teacher t " +
                            "LEFT JOIN c.classroom cl", Object[].class);

            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
                Course course = (Course) result[0];
                Teacher teacher = (Teacher) result[1];
                Classroom classroom = (Classroom) result[2];
                if (course != null) {
                    System.out.print(course.getId() + "\t" +
                            course.getName());
                }
                if (teacher != null) {
                    System.out.print(
                            teacher.getFirstName() + "\t" +
                                    teacher.getLastName());
                } else {
                    System.out.print(
                            "\t" + "N/A" + "\t" +
                                    "N/A");
                }
                if (classroom != null) {
                    System.out.println(classroom.getClassroomName() + "\n");
                } else System.out.println("\t" + "N/A\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
    }

    

    public static void showGrades() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Grade> query = em.createQuery("SELECT g FROM Grade g", Grade.class);
        List<Grade> grades = query.getResultList();
        String headerGradeID = "GID: ";
        String headerGradeName = "Grade: ";
        String format = "%-20s%s";
        System.out.printf(format, headerGradeID, headerGradeName + "\n");
        for (Grade g : grades) {
            System.out.printf(format, g.getId(), g.getName() + "\n");
        }
        em.close();
    }

    public static void showStudentCourseGrade() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<StudentCourseGrade> query = em.createQuery("SELECT scg " +
                "FROM StudentCourseGrade scg", StudentCourseGrade.class);
        List<StudentCourseGrade> list = query.getResultList();
        String headerID = "ID: ";
        String headerGradeID = "G-ID: ";
        String headerStudentID = "S-ID: ";
        String headerCourseID = "C-ID: ";
        String format = "%-10s%-20s%-10s%s";
        System.out.printf(format, headerID, headerGradeID, headerStudentID, headerCourseID + "\n");
        for (StudentCourseGrade scg : list) {
            System.out.printf(format, scg.getId(),
                    scg.getCourse().getName(),
                    scg.getGrade().getName(),
                    scg.getStudent().getFirstName() + "\n");
        }
        em.close();
    }

    public static void showClassrooms(){
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Classroom> query = em.createQuery("SELECT c FROM Classroom c", Classroom.class);
        List<Classroom> classrooms = query.getResultList();
        if (!classrooms.isEmpty()) {
            for (Classroom c : classrooms) {
                System.out.println(c.getId() + "\t" +
                        c.getClassroomName() + "\t" +
                        c.getClassroomCapacity());
            }
        }
        em.close();
    }
}