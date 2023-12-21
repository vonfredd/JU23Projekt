package org.example.demo.combiner;

import classes.Classroom;
import classes.Course;
import classes.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.ArrayList;
import java.util.List;

public class JavaFXMethods {

    //For javaFX
    public static List<String> showCoursesFX() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c, t, cl " +
                        "FROM Course c " +
                        "LEFT JOIN c.teacher t " +
                        "LEFT JOIN c.classroom cl", Object[].class);

        List<Object[]> resultList = query.getResultList();
        String format = "%-10s%-20s%-30s%s%n";
        System.out.printf(format, "ID:", "Course:", "Teacher:", "Classroom:");
        System.out.printf(format, "--", "------", "---------", "----------");

        List<String> returnToFX = new ArrayList<>();
        String headerFormat = String.format("%-20s%-30s%s","Course","Teacher","Classroom");

        returnToFX.add(headerFormat);

        for (Object[] result : resultList) {
            Course course = (Course) result[0];
            Teacher teacher = (Teacher) result[1];
            Classroom classroom = (Classroom) result[2];
            if (course != null) {
                System.out.printf("%-10d%-20s", course.getId(), course.getName());
            }
            if (teacher != null) {
                System.out.printf("%-30s", teacher.getFirstName() + " " + teacher.getLastName());
            } else {
                System.out.printf("%-30s", "N/A");
            }
            if (classroom != null) {
                System.out.printf("%s%n", classroom.getClassroomName());
            } else {
                System.out.printf("%s%n", "N/A");
            }

            if (teacher != null && classroom != null) {
                String s = String.format("%-20s%-30s%s",
                        course.getName(),
                        teacher.getFirstName(),
                        classroom.getClassroomName());
                returnToFX.add(s);
                CourseTeacherClassroom ctc = new CourseTeacherClassroom(course.getName(), teacher.getFirstName(), classroom.getClassroomName());
            }
        }

        return returnToFX;
    }


    //java fx
    public static String readMenu(){
        String readMenu = """
                    Show
                    -------------------
                    1. All courses
                    2. Student grades for a course
                    3. Show total amount of students
                    0. Back
                    """;
        return readMenu;
    }
}
