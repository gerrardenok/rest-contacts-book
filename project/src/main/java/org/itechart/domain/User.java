package org.itechart.domain;

import org.hibernate.validator.constraints.Email;
import org.itechart.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AngularEntity<User, UserResource> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(max = 255)
    private String userName;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private boolean enabled;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "user")
    private List<Contact> contacts;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date modified;

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.contacts = new ArrayList<Contact>();
        this.modified = new Date();
    }

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public long getId() {
        return id;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public UserResource toResource() {
        UserResource resouce = new UserResource();
        resouce.fill(this);
        return resouce;
    }

    @Override
    public User merge(UserResource resource) {
        setEmail(resource.email);
        setUserName(resource.userName);
        setPassword(resource.password);
        return this;
    }
}
