package org.itechart.web;

import org.itechart.domain.User;
import org.itechart.repository.UserRepository;
import org.itechart.service.UserService;
import org.itechart.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/user")
public class UserRestController extends AngularResourceController<User, UserResource, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    protected UserRepository getEntityRepository() {
        return userRepository;
    }

    @Override
    protected UserService getUserService() {
        return userService;
    }

}