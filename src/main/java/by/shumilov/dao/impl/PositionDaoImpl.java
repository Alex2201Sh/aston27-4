package by.shumilov.dao.impl;

import by.shumilov.bean.Position;
import by.shumilov.dao.CommonDaoUtils;
import by.shumilov.dao.PositionDao;
import by.shumilov.dao.db.ConnectionCreator;
import by.shumilov.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoImpl implements PositionDao {

    private static final String SQL_SELECT_ALL_POSITIONS =
            "SELECT id, name FROM public.positions;";

    private static final String SQL_SELECT_POSITION_BY_ID =
            "SELECT id, name FROM public.positions WHERE id = ?;";

    private static final String SQL_DELETE_POSITION_BY_ID =
            "DELETE FROM positions WHERE id = ?";

    private static final String SQL_SELECT_POSITIONS_BY_EMPLOYEE_ID =
            "SELECT p.id as position_id, p.name as position_name FROM public.positions p\n " +
                    "LEFT JOIN employees_positions ep on p.id = ep.position_id " +
                    "WHERE ep.employee_id = ?";

    private static final String SQL_DELETE_POSITION_FROM_EMPLOYEES_POSITIONS = "" +
            "DELETE FROM employees_positions WHERE position_id = ?";

    @Override
    public List<Position> findAll() throws DaoException {
        List<Position> positionList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_POSITIONS);
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("id"));
                position.setName(resultSet.getString("name"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return positionList;
    }

    @Override
    public Position findEntityById(Integer id) throws DaoException {
        Position positionById = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_POSITION_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                positionById = new Position();
                positionById.setId(resultSet.getInt("id"));
                positionById.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return positionById;
    }

    @Override
    public boolean delete(Position position) throws DaoException {
        return delete(position.getId());
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return new CommonDaoUtils().deleteEntityById(id, SQL_DELETE_POSITION_BY_ID);
    }

    @Override
    public boolean create(Position position) throws DaoException {
        return false;
    }

    @Override
    public Position update(Position position) throws DaoException {
        return null;
    }

    @Override
    public List<Position> findPositionByEmployeeId(int employeeId) throws DaoException {
        List<Position> listByEmployee = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_POSITIONS_BY_EMPLOYEE_ID);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("position_id"));
                position.setName(resultSet.getString("position_name"));
                listByEmployee.add(position);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listByEmployee;
    }

    @Override
    public boolean deletePositionFromEmployeesPositionsTable(int positionId) {
        return new CommonDaoUtils().deleteEntityById(positionId, SQL_DELETE_POSITION_FROM_EMPLOYEES_POSITIONS);
    }
}
