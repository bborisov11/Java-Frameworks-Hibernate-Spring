package testProblem3;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "student_id",referencedColumnName = "person_id")
public class Student extends Person{

    private int averageGrade;
    private String attendance;
    private Set<Course>  courses;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "averageGrade"),
    inverseJoinColumns = @JoinColumn(name = "nameDescription"))
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Student() {
    }
    @Column(name = "averageGrade")
    public int getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(int averageGrade) {
        this.averageGrade = averageGrade;
    }
    @Column(name = "attendance")
    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
