package com.dsa360.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_us")
public class ContactUsEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String mobile_number;
    private String message;

    public ContactUsEntity() {
    }

    public ContactUsEntity(String id, String name, String email, String mobile_number, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
