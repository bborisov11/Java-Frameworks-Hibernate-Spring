package testProblem3;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Course {

    private String nameDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Student> students;

    @ManyToMany(mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Course() {
    }
    @Id
    @Column(name = "nameDescription")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getNameDescription() {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }
    @Column(name = "startDate", columnDefinition = "DATETIME")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @Column(name = "endDate", columnDefinition = "DATETIME")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
