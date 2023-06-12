package by.shumilov.dao.impl;

import by.shumilov.bean.Department;
import by.shumilov.bean.Employee;
import by.shumilov.dao.EmployeeDao;
import by.shumilov.dao.db.ConnectionCreator;
import by.shumilov.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String SQL_SELECT_ALL_EMPLOYEES =
            "SELECT e.id, e.name, e.surname, e.telephone, e.department_id " +
                    "FROM public.employees e;";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID =
            "SELECT e.id, e.name, e.surname, e.telephone, e.department_id " +
                    "FROM public.employees e " +
                    "WHERE e.id = ?;";
    private static final String SQL_SELECT_EMPLOYEE_BY_SURNAME =
            "SELECT e.id, e.name, e.surname, e.telephone, e.department_id " +
                    "FROM public.employees e " +
                    "WHERE surname = ?;";
    private static final String SQL_DELETE_EMPLOYEE_BY_ID =
            "DELETE FROM employees WHERE id = ?;";

    private static final String SQL_DELETE_EMPLOYEE_FROM_EMPLOYEES_POSITIONS = "" +
            "DELETE FROM employees_positions WHERE employee_id = ?";

    private static final String SQL_SAVE_EMPLOYEE =
            "INSERT INTO employees (name, surname, telephone, department_id)\n" +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE_EMPLOYEE =
            "UPDATE employees SET name = ?, surname = ?, " +
                    "telephone = ?, department_id = ? " +
                    "WHERE id = ?";

    @Override
    public List<Employee> findAll() throws DaoException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EMPLOYEES);
            while (resultSet.next()) {
                Employee employee = new Employee();
                setParameters(resultSet, employee);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return employeeList;
    }

    @Override
    public Employee findEntityById(Integer id){
        Employee employeeById = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeById = new Employee();
                setParameters(resultSet, employeeById);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return employeeById;
    }

    @Override
    public boolean delete(Employee employee) throws DaoException {
        return delete(employee.getId());
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
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
    public boolean create(Employee employee) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SAVE_EMPLOYEE);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getTelephone());
            statement.setInt(4, employee.getDepartment().getId());
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
    public Employee update(Employee employee) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Employee employeeFromDb = findEntityById(employee.getId());
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE);
            statement.setString(1, employee.getFirstName() != null ? employee.getFirstName() : employeeFromDb.getFirstName());
            statement.setString(2, employee.getSurname() != null ? employee.getSurname() : employeeFromDb.getSurname());
            statement.setString(3, employee.getTelephone() != null ? employee.getTelephone() : employeeFromDb.getTelephone());
            statement.setInt(4, employee.getDepartment() != null ? employee.getDepartment().getId() : employeeFromDb.getDepartment().getId());
            statement.setInt(5, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return findEntityById(employee.getId());
    }

    @Override
    public List<Employee> findEmployeeBySurname(String surname) throws DaoException {
        List<Employee> listBySurname = new ArrayList<>();
        Employee employeeBySurname;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_SURNAME);
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeBySurname = new Employee();
                setParameters(resultSet, employeeBySurname);
                listBySurname.add(employeeBySurname);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listBySurname;
    }

    @Override
    public boolean deleteEmployeeFromEmployeesPositionsTable(int employeeId){
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_FROM_EMPLOYEES_POSITIONS);
            statement.setInt(1, employeeId);
            int i = statement.executeUpdate();
            if (i > 0) result = true;
        } catch (SQLException e) {
            try {
                throw new DaoException(e);
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }


    private void setParameters(ResultSet resultSet, Employee employee) throws SQLException {
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setTelephone(resultSet.getString("telephone"));
        Department department = new Department();
        department.setId(resultSet.getInt("department_id"));
        employee.setDepartment(department);
    }
}
