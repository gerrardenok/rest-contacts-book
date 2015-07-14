package org.itechart.web.resource;

import org.itechart.domain.User;

public class UserResource extends AngularResource<User> {
    public String userName;
    public String email;
    public String password;

    @Override
    public void fill(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public User toEntity() {
        User u = new User();
        u.setId(id);
        u.setUserName(this.userName);
        u.setPassword(this.password);
        return u;
    }
}
