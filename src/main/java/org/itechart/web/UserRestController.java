package org.itechart.web;

import org.itechart.domain.User;
import org.itechart.repository.UserRepository;
import org.itechart.service.UserService;
import org.itechart.web.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/rest/api/user/current", method= RequestMethod.GET)
    @Secured("normal_user")
    public UserForm getLoggedIn() {
        return new UserForm(userService.getLoggedIn());
    }

    @RequestMapping(value = "/rest/api/user/{id}", method= RequestMethod.PUT)
    @Secured("admin")
    public UserForm updateUserInfo(@PathVariable(value="id") String id, @RequestBody UserForm form) {
        User user = userRepository.findOne(Long.parseLong(id));
        userService.update(user, form);
        return form;
    }

}