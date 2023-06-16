package by.shumilov.dao;

import by.shumilov.entity.Employee;
import by.shumilov.dao.exception.DaoException;

import java.util.List;

public interface EmployeeDao extends BaseDao<Integer, Employee> {
    List<Employee> findEmployeeBySurname(String surname) throws DaoException;
    boolean deleteEmployeeFromEmployeesPositionsTable(int employeeId);
}
