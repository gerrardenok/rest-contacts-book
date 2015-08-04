package org.itechart.web.resource;

import org.itechart.domain.User;

public abstract class AngularResource<Entity> {

    public Long id;

    public AngularResource(){};

    public AngularResource(Entity entity){
        this.fill(entity);
    };

    public abstract void fill(Entity entity);

    public abstract Entity buildEntity(User user);

}
