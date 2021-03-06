package org.itechart.web.resource;

import org.itechart.domain.Contact;
import org.itechart.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactResource extends AngularResource<Contact> {

    @NotNull
    @Size(max = 255)
    public String name;

    @NotNull
    @Size(max = 255)
    public String tel;

    public static Page<ContactResource> transform(Page<Contact> page) {
        List<Contact> contacts = page.getContent();
        List<ContactResource> out = new ArrayList<ContactResource>();
        for(Contact contact : contacts) {
            ContactResource transformed = new ContactResource();
            transformed.fill(contact);
            out.add(transformed);
        }
        return new PageImpl<ContactResource>(out, new PageRequest(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    @Override
    public void fill(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.tel = contact.getTel();
    }

    @Override
    public Contact buildEntity(User user) {
        Contact contact = new Contact();
        contact.setUser(user);
        contact.setName(this.name);
        contact.setTel(this.tel);
        contact.setModified(new Date());
        return contact;
    }
}
