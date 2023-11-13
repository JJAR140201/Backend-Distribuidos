package com.taller.IRepository;

import com.taller.Entity.Auditoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository <T extends Auditoria, ID> extends MongoRepository<T, ID> {
}
