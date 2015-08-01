package org.itechart.web;

import org.itechart.domain.User;
import org.itechart.exceptions.EmailNotUniqueException;
import org.itechart.service.UserService;
import org.itechart.web.form.UserRegistrationForm;
import org.itechart.web.resource.ScalarResource;
import org.itechart.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rest/api/user/current", method= RequestMethod.GET)
    @Secured("normal_user")
    public UserResource getLoggedIn() {
        return userService.getLoggedIn().toResource();
    }

    @Transactional
    @RequestMapping(value = "/rest/api/auth/registration", method= RequestMethod.POST)
    public UserResource registration(@RequestBody UserRegistrationForm body) {
        User user = userService.createUser(body.userName, body.email, body.password);
        return user.toResource();
    }

    @RequestMapping(value = "/rest/api/auth/checkEmail", method = RequestMethod.GET)
    public ScalarResource checkEmail(@RequestParam(value="email") String email) {
        if (!userService.isEmailUnique(email)) {
            throw new EmailNotUniqueException(email);
        }
        return new ScalarResource(email);
    }

}