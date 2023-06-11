package by.shumilov.dao;

import by.shumilov.bean.Position;
import by.shumilov.dao.exception.DaoException;

import java.util.List;

public interface PositionDao extends BaseDao<Integer, Position> {
    List<Position> findPositionByEmployeeId(int employeeId) throws DaoException;

}
