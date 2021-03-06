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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String userName, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userName, email, encodedPassword);
        Set<UserRole> roles = new HashSet<UserRole>();
        roles.add(new UserRole(user, "normal_user"));
        user.setUserRoles(roles);
        user = userRepository.save(user);
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
        String encodedPassword = passwordEncoder.encode(form.password);
        model.setUserName(form.userName);
        model.setEmail(form.email);
        model.setPassword(encodedPassword);
        return userRepository.save(model);
    }

    @Override
    public User getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        return userRepository.findTopByEmailAndEnabled(details.getUsername(), true);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).size() == 0;
    }

}
