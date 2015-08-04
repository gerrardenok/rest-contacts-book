package org.itechart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Entity not found")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity not found" );
    }

    public EntityNotFoundException(Long id) {
        super("Entity not found with id = " + id);
    }

}
