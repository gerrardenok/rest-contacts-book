package org.itechart.web;

import org.itechart.domain.Contact;
import org.itechart.domain.User;
import org.itechart.exceptions.EmailNotUniqueException;
import org.itechart.repository.ContactRepository;
import org.itechart.service.UserService;
import org.itechart.web.resource.ContactResource;
import org.itechart.web.resource.ScalarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/contact")
public class ContactRestController extends AngularResourceController<Contact, ContactResource, ContactRepository> {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @Override
    protected ContactRepository getEntityRepository() {
        return contactRepository;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @Secured("normal_user")
    public Page<ContactResource> list(@RequestParam(value="page", defaultValue = "0") String pageNum, @RequestParam(value="size", defaultValue = "20") String pageSize) {
        Pageable request = new PageRequest(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        User currentUser = userService.getLoggedIn();
        Page<Contact> page = contactRepository.findByUserId(currentUser.getId(), request);
        return ContactResource.transform(page);
    }
}