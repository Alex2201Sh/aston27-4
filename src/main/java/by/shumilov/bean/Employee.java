package by.shumilov.bean;

import java.util.List;

public class Employee extends BaseEntity {
    private String firstName;
    private String surname;
    private String telephone;
    private Department department;
    private List<Position> positionList;

    public Employee() {
    }

    public Employee(String firstName, String surname, String telephone, Department department, List<Position> positionList) {
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
        this.department = department;
        this.positionList = positionList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", department=" + department +
                ", id=" + id +
                '}';
    }
}
