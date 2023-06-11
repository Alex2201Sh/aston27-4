package by.shumilov.dao.impl;

import by.shumilov.bean.Department;
import by.shumilov.bean.Employee;
import by.shumilov.dao.DepartmentDao;
import by.shumilov.dao.db.ConnectionCreator;
import by.shumilov.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final String SQL_SELECT_DEPARTMENT_BY_ID =
            "SELECT id, name FROM public.departments WHERE id = ?;";
    @Override
    public List<Department> findAll() throws DaoException {
        return null;
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
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Department department) throws DaoException {
        return false;
    }

    @Override
    public Department update(Department department) throws DaoException {
        return null;
    }
}
