package oleg.bryl.dao;

import oleg.bryl.entity.BaseEntity;

public interface Dao<T extends BaseEntity> {

    T insert(T item) throws Exception;

    T findById(int id) throws Exception;

    void update(T item) throws Exception;

    void delete(T item) throws Exception;
}
