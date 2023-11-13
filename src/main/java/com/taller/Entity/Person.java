package com.taller.Entity;

import org.springframework.data.mongodb.core.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Collation("person")
public class Person extends Auditoria{
    @Field
    private String name;

    @Field
    private String lastname;

    @Field
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
