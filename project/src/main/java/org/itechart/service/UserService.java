package org.itechart.service;

import org.itechart.domain.User;
import org.itechart.web.resource.UserResource;

public interface UserService {

    public User createUser(String userName, String email, String password);

    public void deleteUser(Long userId);

    public User update(User model, UserResource form);

    public User getLoggedIn();

    public boolean isEmailUnique(String email);

}

