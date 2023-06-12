package by.shumilov.dao.impl;

import by.shumilov.TestDataBuilder;
import by.shumilov.bean.Employee;
import by.shumilov.dao.exception.DaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

class EmployeeDaoImplTest {

    private final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    private final TestDataBuilder testDataBuilder = new TestDataBuilder();

    @BeforeEach
    void fillTables() throws SQLException {
        testDataBuilder.createTables();
    }
    @AfterEach
    void clearData(){
        testDataBuilder.close();
    }

    @Test
    void findAll() throws DaoException, SQLException {
        List<Employee> all = employeeDao.findAll();
        System.out.println(all);


    }

    @Test
    void findEntityById() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void findEmployeeBySurname() {
    }
}