package org.example.demo.combiner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CourseTeacherClassroom {
    private String courseName;
    private String teacherName;
    private String classroomName;

    public static ObservableList<CourseTeacherClassroom> collectedCombinators = FXCollections.observableArrayList();

    public CourseTeacherClassroom(String courseName, String teacherName, String classroomName) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.classroomName = classroomName;
        addThis();
    }

    private void addThis() {
        for (int i = 0; i < collectedCombinators.size(); i++) {
            if(collectedCombinators.get(i).courseName.equals(this.courseName))
                return;
        }
            if (collectedCombinators == null) {
                collectedCombinators = FXCollections.observableArrayList();
            }
            collectedCombinators.add(this);

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public static List<CourseTeacherClassroom> getCollectedCombinators() {
        return collectedCombinators;
    }

    public static void setCollectedCombinators(List<CourseTeacherClassroom> collectedCombinators) {
        CourseTeacherClassroom.collectedCombinators = (ObservableList<CourseTeacherClassroom>) collectedCombinators;
    }
}
