package org.itechart.domain;

import org.itechart.web.resource.ContactResource;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user_contacts")
public class Contact extends AngularEntity<Contact, ContactResource> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String tel;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date modified;

    public Contact() {
    }

    @Override
    public ContactResource toResource() {
        ContactResource resource = new ContactResource();
        resource.id = getId();
        resource.tel = getTel();
        resource.name = getName();
        return resource;
    }

    @Override
    public Contact merge(ContactResource resource) {
        setTel(resource.tel);
        setName(resource.name);
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getModified() {
        return modified;
    }
}
