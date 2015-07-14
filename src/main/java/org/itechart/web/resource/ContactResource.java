package org.itechart.web.resource;

import org.itechart.domain.Contact;

public class ContactResource extends AngularResource<Contact> {

    public String tel;

    @Override
    public void fill(Contact contact) {
        this.id = contact.getId();
        this.tel = contact.getTel();
    }

    @Override
    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setId(this.id);
        contact.setTel(this.tel);
        return contact;
    }
}
