package com.taller.Service;

import com.taller.Entity.Person;
import com.taller.IRepository.IBaseRepository;
import com.taller.IRepository.IPersonRepository;
import com.taller.IService.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonService extends BaseService<Person> implements IPersonService {
    @Override
    protected IBaseRepository<Person, String> getRepository() {
        return repository;
    }

    @Autowired
    private IPersonRepository repository;
}
