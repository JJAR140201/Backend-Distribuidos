package com.taller.Controller;

import com.taller.Entity.Person;
import com.taller.IService.IPersonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/api/person")
public class PersonController extends BaseController<Person, IPersonService> {
    public PersonController(IPersonService service) {
        super(service, "Person");
    }
}
