package org.itechart.service.internal;

import org.itechart.domain.User;
import org.itechart.domain.UserRole;
import org.itechart.repository.UserRepository;
import org.itechart.repository.UserRoleRepository;
import org.itechart.service.UserService;
import org.itechart.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User createUser(String userName, String email, String password,  Boolean isAdmin) {
        User user = new User(userName, email, password);
        user = userRepository.save(user);
        userRoleRepository.save(new UserRole(user, "normal_user"));
        if (isAdmin) {
            userRoleRepository.save(new UserRole(user, "admin"));
        }
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findOne(userId);
        if(user != null) {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @Override
    public User update(User model, UserResource form) {
        model.setUserName(form.userName);
        model.setEmail(form.email);
        model.setPassword(form.password);
        return userRepository.save(model);
    }

    @Override
    public User getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        return userRepository.findTopByEmailAndPasswordAndEnabled(details.getUsername(), details.getPassword(), true);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).size() == 0;
    }

}
