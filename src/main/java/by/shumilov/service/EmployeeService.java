package by.shumilov.service;

import by.shumilov.entity.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Integer, Employee> {

    List<Employee> findEmployeeBySurname(String surname);

}
