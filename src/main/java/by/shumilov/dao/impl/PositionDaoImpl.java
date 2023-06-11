package by.shumilov.dao.impl;

import by.shumilov.bean.Position;
import by.shumilov.dao.PositionDao;
import by.shumilov.dao.db.ConnectionCreator;
import by.shumilov.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoImpl implements PositionDao {

    private static final String SQL_SELECT_POSITION_BY_ID =
            "SELECT id, name FROM public.positions WHERE id = ?;";

    private static final String SQL_SELECT_POSITIONS_BY_EMPLOYEE_ID =
            "SELECT p.id as position_id, p.name as position_name FROM public.positions p\n " +
                    "LEFT JOIN employees_positions ep on p.id = ep.position_id " +
                    "WHERE ep.employee_id = ?";

    @Override
    public List<Position> findAll() throws DaoException {
        return null;
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
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
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
}
