package by.shumilov.service.impl;

import by.shumilov.bean.Department;
import by.shumilov.dao.DepartmentDao;
import by.shumilov.dao.exception.DaoException;
import by.shumilov.dao.impl.DepartmentDaoImpl;
import by.shumilov.service.DepartmentService;
import java.util.Collections;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Department> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Department findEntityById(Integer id) {
        try {
            return departmentDao.findEntityById(id);
        } catch (DaoException e) {
            throw new RuntimeException("Department with id: " + id + " doesn't exist. " + e);
        }
    }

    @Override
    public boolean delete(Department department) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Department department) {
        return false;
    }

    @Override
    public Department update(Department department) {
        return null;
    }
}
