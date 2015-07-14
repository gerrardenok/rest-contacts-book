package org.itechart.domain;

import org.itechart.web.resource.AngularResource;

public abstract class AngularEntity<Entity, Resource extends AngularResource<Entity>> {

    public abstract Resource toResource();

    public abstract Entity merge(Resource resource);

}
