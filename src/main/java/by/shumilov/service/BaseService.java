package by.shumilov.service;

import by.shumilov.bean.BaseEntity;

import java.util.List;

public interface BaseService<K, T extends BaseEntity> {
    List<T> findAll();

    T findEntityById(K id);

    boolean delete(T t);

    boolean delete(K id);

    boolean create(T t);

    T update(T t);
}
