package by.shumilov.dao.impl;

import by.shumilov.bean.Department;
import by.shumilov.dao.exception.DaoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDaoImplTest {

    @Test
    void findAll() throws DaoException {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.create(new Department("TESTDEP"));
        List<Department> all = departmentDao.findAll();
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
}