package org.itechart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Email already exist in db")
public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException() {
        super("Cannot find user card with id = null" );
    }

    public EmailAlreadyExistException(Long id) {
        super("Cannot find user card with id = " + id);
    }

}
