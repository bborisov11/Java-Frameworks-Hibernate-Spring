package entities;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate registeredOn;

    public Student() {
    }

    public Student(String name, LocalDate registeredOn) {
        this.name = name;
        this.registeredOn = registeredOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registeredOn=" + registeredOn +
                '}';
    }
}
