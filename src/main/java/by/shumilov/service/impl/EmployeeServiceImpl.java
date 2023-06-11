package by.shumilov.service.impl;

import by.shumilov.bean.Employee;
import by.shumilov.dao.EmployeeDao;
import by.shumilov.dao.exception.DaoException;
import by.shumilov.dao.impl.EmployeeDaoImpl;
import by.shumilov.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public List<Employee> findAll() {
        try {
            return employeeDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findEntityById(Integer id) {
        try {
            return employeeDao.findEntityById(id);
        } catch (DaoException e) {
            throw new RuntimeException("id " + id + " is not exists. " + e);
        }
    }

    @Override
    public boolean delete(Employee employee) {
        try {
            return employeeDao.delete(employee);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with employee: " + employee + ". " + e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            return employeeDao.delete(id);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with employee with id: " + id + ". " + e);
        }
    }

    @Override
    public boolean create(Employee employee) {
        try {
            return employeeDao.create(employee);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with during creation of employee: " + employee + ". " + e);
        }
    }

    @Override
    public Employee update(Employee employee) {
        try {
            return employeeDao.update(employee);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with during update of employee: " + employee + ". " + e);
        }
    }
}
