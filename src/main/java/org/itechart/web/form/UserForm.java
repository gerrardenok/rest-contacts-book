package org.itechart.web.form;

import org.itechart.domain.User;

public class UserForm {
    public Long id;
    public String userName;
    public String email;
    public String password;

    public UserForm(){}

    public UserForm(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
