package by.shumilov.service.impl;

import by.shumilov.entity.Position;
import by.shumilov.dao.PositionDao;
import by.shumilov.dao.exception.DaoException;
import by.shumilov.dao.impl.PositionDaoImpl;
import by.shumilov.service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {

    private final PositionDao positionDao = new PositionDaoImpl();

    @Override
    public List<Position> findAll() {
        try {
            return positionDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Position findEntityById(Integer id) {
        try {
            return positionDao.findEntityById(id);
        } catch (DaoException e) {
            throw new RuntimeException("Position with id: " + id + " doesn't exist. " + e);
        }
    }

    @Override
    public boolean delete(Position position) {
        boolean result;

        try {
            boolean a = positionDao.deletePositionFromEmployeesPositionsTable(position.getId());
            boolean b = positionDao.delete(position);
            result = a && b;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result;
        try {
            boolean a = positionDao.deletePositionFromEmployeesPositionsTable(id);
            boolean b = positionDao.delete(id);
            result = a && b;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean create(Position position) {
        return false;
    }

    @Override
    public Position update(Position position) {
        return null;
    }

    @Override
    public List<Position> findPositionByEmployeeId(int employeeId) {
        try {
            return positionDao.findPositionByEmployeeId(employeeId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
