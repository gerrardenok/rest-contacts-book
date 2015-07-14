package org.itechart.web;

import org.itechart.domain.AngularEntity;
import org.itechart.exceptions.EntityNotFoundException;
import org.itechart.web.resource.AngularResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.annotation.Secured;

public abstract class AngularResourceController<
        Entity extends AngularEntity<Entity, Resource>,
        Resource extends AngularResource<Entity>,
        Repository extends CrudRepository<Entity, Long>>  {

    protected abstract Repository getEntityRepository();

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    @Secured("normal_user")
    public Resource get(@PathVariable("id") Long id) {
        return doGet(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured("normal_user")
    public Resource create(@RequestBody Resource body) {
        return doCreate(body);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT)
    @Secured("normal_user")
    public Resource update(@PathVariable("id") Long id, @RequestBody Resource body) {
        return doUpdate(id, body);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE)
    @Secured("normal_user")
    public void delete(@PathVariable("id") Long id) {
        doDelete(id);
    };

    // --------------------------------------------------------- //
    // --------------------------------------------------------- //

    protected Resource doGet(Long id) {
        Entity e = getEntityRepository().findOne(id);
        if (e == null) throw new EntityNotFoundException();
        return e.toResource();
    }

    protected Resource doCreate(Resource body) {
        Entity e = body.toEntity();
        e = getEntityRepository().save(e);
        return e.toResource();
    }

    protected Resource doUpdate(Long id, Resource body) {
        Entity e = getEntityRepository().findOne(id);
        if (e == null) throw new EntityNotFoundException(id);
        e.merge(body);
        e = getEntityRepository().save(e);
        return e.toResource();
    }

    public void doDelete(Long id) {
        getEntityRepository().delete(id);
    };

}
