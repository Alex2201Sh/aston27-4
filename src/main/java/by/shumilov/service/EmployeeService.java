package by.shumilov.service;

import by.shumilov.bean.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Integer, Employee> {

    List<Employee> findEmployeeBySurname(String surname);

}
