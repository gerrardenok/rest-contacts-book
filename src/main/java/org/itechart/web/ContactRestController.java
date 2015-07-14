package org.itechart.web;

import org.itechart.domain.Contact;
import org.itechart.repository.ContactRepository;
import org.itechart.web.resource.ContactResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/contact")
public class ContactRestController extends AngularResourceController<Contact, ContactResource, ContactRepository> {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    protected ContactRepository getEntityRepository() {
        return contactRepository;
    }
}