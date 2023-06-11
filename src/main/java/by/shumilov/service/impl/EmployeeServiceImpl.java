package by.shumilov.service.impl;

import by.shumilov.bean.Employee;
import by.shumilov.dao.EmployeeDao;
import by.shumilov.dao.exception.DaoException;
import by.shumilov.dao.impl.EmployeeDaoImpl;
import by.shumilov.service.DepartmentService;
import by.shumilov.service.EmployeeService;
import by.shumilov.service.PositionService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList;
        try {
            employeeList = employeeDao.findAll();
            employeeList.forEach(employee ->
            {
                employee
                        .setDepartment(departmentService
                                .findEntityById(
                                        employee.getDepartment().getId()));
                employee.setPositionList(positionService
                        .findPositionByEmployeeId(employee.getId()));
            });

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee findEntityById(Integer id) {
        Employee employeeById;
        try {
            employeeById = employeeDao.findEntityById(id);
            employeeById.setDepartment(departmentService
                    .findEntityById(
                            employeeById.getDepartment().getId()));
            employeeById.setPositionList(positionService.findPositionByEmployeeId(id));
        } catch (DaoException e) {
            throw new RuntimeException("id " + id + " is not exists. " + e);
        }
        return employeeById;
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
