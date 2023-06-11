package by.shumilov.parser;

import by.shumilov.bean.Department;
import by.shumilov.bean.Employee;
import by.shumilov.bean.Position;
import by.shumilov.dao.PositionDao;
import by.shumilov.dao.exception.DaoException;
import by.shumilov.dao.impl.EmployeeDaoImpl;
import by.shumilov.dao.impl.PositionDaoImpl;
import by.shumilov.service.EmployeeService;
import by.shumilov.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

class StreamTest {
    @Test
    void streamTest() throws DaoException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        List<Employee> all = employeeDao.findAll();
        System.out.println(all);

        Employee entityById = employeeDao.findEntityById(1);
        System.out.println(entityById);

        List<Employee> jolie = employeeDao.findEmployeeBySurname("Jolie");
        System.out.println(jolie);
    }

    @Test
    void saveEmp() throws DaoException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

        Employee employee = new Employee();
        employee.setFirstName("test");
        employee.setSurname("test sur");
        employee.setTelephone("test tel3");
        Department testDepartment = new Department("test department");
        testDepartment.setId(1);
        employee.setDepartment(testDepartment);

        boolean b = employeeDao.create(employee);


        System.out.println(b);
    }

    @Test
    void updateTest() throws DaoException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee entityById = employeeDao.findEntityById(18);
        entityById.setFirstName("asdfghjkl;");
        entityById.setSurname("qwertyuio");
        employeeDao.update(entityById);
    }

    @Test
    void checkFindAllService() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> all = employeeService.findAll();
        all.stream().forEach(System.out::println);
    }

    @Test
    void checkFindOneService() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee entityById = employeeService.findEntityById(1);
        System.out.println(entityById);
    }

    @Test
    void checkPositionById() throws DaoException {
        PositionDao positionDao = new PositionDaoImpl();
        Position entityById = positionDao.findEntityById(2);
        System.out.println(entityById);
    }

    @Test
    void checkFindPosByEmpId() throws DaoException {
        PositionDao positionDao = new PositionDaoImpl();
        List<Position> positionByEmployeeId = positionDao.findPositionByEmployeeId(1);
        System.out.println(positionByEmployeeId);
    }
}
