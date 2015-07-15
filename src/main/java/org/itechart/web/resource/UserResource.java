package org.itechart.web.resource;

import org.itechart.domain.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserResource extends AngularResource<User> {

    @NotNull
    @Size(max = 255)
    public String userName;

    @NotNull
    @Size(max = 255)
    public String email;

    @NotNull
    @Size(max = 255)
    public String password;

    @Override
    public void fill(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public User buildEntity(User user) {
        User u = new User();
        u.setId(id);
        u.setUserName(this.userName);
        u.setPassword(this.password);
        return u;
    }
}
