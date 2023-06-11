package by.shumilov.service;

import by.shumilov.bean.Position;

import java.util.List;

public interface PositionService extends BaseService<Integer, Position> {
    List<Position> findPositionByEmployeeId(int employeeId);

}
