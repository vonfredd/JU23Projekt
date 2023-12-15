package classes;

import jakarta.persistence.*;

@Entity
@Table(name = "Classroom", schema = "projekt7")

public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroomId", nullable = false)
    private Integer id;

    @Column(name = "classroomName", nullable = false, length = 30)
    private String classroomName;

    @Column(name = "classroomCapacity", nullable = false)
    private Integer classroomCapacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Integer getClassroomCapacity() {
        return classroomCapacity;
    }

    public void setClassroomCapacity(Integer classroomCapacity) {
        this.classroomCapacity = classroomCapacity;
    }

}