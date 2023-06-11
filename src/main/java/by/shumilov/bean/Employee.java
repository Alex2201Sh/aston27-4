package by.shumilov.bean;

import java.util.List;
import java.util.Objects;

public class Employee extends BaseEntity {
    private String firstName;
    private String surname;
    private String telephone;
    private Department department;
    private List<Position> positionList;

    public Employee() {
    }

    public Employee(String firstName, String surname, String telephone, Department department) {
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
        this.department = department;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(firstName, employee.firstName)) return false;
        if (!Objects.equals(surname, employee.surname)) return false;
        if (!Objects.equals(telephone, employee.telephone)) return false;
        return Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
