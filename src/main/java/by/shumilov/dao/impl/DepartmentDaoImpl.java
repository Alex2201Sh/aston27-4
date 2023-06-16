package by.shumilov.dao.impl;

import by.shumilov.entity.Department;
import by.shumilov.dao.CommonDaoUtils;
import by.shumilov.dao.DepartmentDao;
import by.shumilov.dao.db.ConnectionCreator;
import by.shumilov.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final String SQL_SELECT_ALL_DEPARTMENTS =
            "SELECT id, name FROM public.departments;";
    private static final String SQL_SELECT_DEPARTMENT_BY_ID =
            "SELECT id, name FROM public.departments WHERE id = ?;";
    private static final String SQL_DELETE_DEPARTMENT_BY_ID =
            "DELETE FROM departments WHERE id = ?";

    private static final String SQL_SAVE_DEPARTMENT = "" +
            "INSERT INTO departments (name) VALUES (?)";

    @Override
    public List<Department> findAll() throws DaoException {
        List<Department> departmentList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_DEPARTMENTS);
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return departmentList;
    }

    @Override
    public Department findEntityById(Integer id) throws DaoException {
        Department departmentById = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departmentById = new Department();
                departmentById.setId(resultSet.getInt("id"));
                departmentById.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return departmentById;
    }

    @Override
    public boolean delete(Department department) throws DaoException {
        return delete(department.getId());
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return new CommonDaoUtils().deleteEntityById(id, SQL_DELETE_DEPARTMENT_BY_ID);
    }

    @Override
    public boolean create(Department department) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SAVE_DEPARTMENT);
            statement.setString(1, department.getName());
            int i = statement.executeUpdate();
            if (i > 0) result = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public Department update(Department department) throws DaoException {
        return null;
    }
}
