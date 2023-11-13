package com.taller.IService;

import com.taller.Entity.Auditoria;

import java.util.List;

public interface IBaseService <T extends Auditoria> {
    List<T> all();
    List<T> findByStateTrue();
    T findById(String id) throws Exception;
    T save(T entity) throws Exception;
    void update(String id, T entity) throws Exception;
    void delete(String id) throws Exception;
}
