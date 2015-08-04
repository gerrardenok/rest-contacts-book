package org.itechart.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationForm {

    @NotNull
    @Size(max = 255)
    public String email;

    @NotNull
    @Size(max = 255)
    public String userName;

    @NotNull
    @Size(max = 255)
    public String password;

}
