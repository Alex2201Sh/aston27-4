package by.shumilov.service;

import by.shumilov.entity.Position;

import java.util.List;

public interface PositionService extends BaseService<Integer, Position> {
    List<Position> findPositionByEmployeeId(int employeeId);

}
