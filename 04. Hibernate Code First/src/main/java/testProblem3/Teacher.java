package testProblem3;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "teacher_id",referencedColumnName = "person_id")
public class Teacher extends Person {

    private String email;
    private BigDecimal salaryPerHour;

    public Teacher() {
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "salaryPerHour")
    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
