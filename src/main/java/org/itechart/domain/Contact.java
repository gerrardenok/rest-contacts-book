package org.itechart.domain;

import org.itechart.web.resource.ContactResource;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_contacts")
public class Contact extends AngularEntity<Contact, ContactResource> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String tel;

    public Contact() {
    }

    @Override
    public ContactResource toResource() {
        ContactResource resource = new ContactResource();
        resource.id = getId();
        resource.tel = getTel();
        return resource;
    }

    @Override
    public Contact merge(ContactResource resource) {
        setTel(resource.tel);
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
