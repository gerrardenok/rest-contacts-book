package org.itechart.service;

import org.itechart.domain.User;
import org.itechart.web.form.UserForm;

public interface UserService {

    public User createUser(String email, String password, Boolean isAdmin, Long score);

    public void deleteUser(Long userId);

    public User update(User model, UserForm form);

    public User getLoggedIn();

}

