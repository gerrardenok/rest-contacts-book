package org.itechart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Email is not unique")
public class EmailNotUniqueException extends RuntimeException {

    public EmailNotUniqueException() {
        super("Email is not unique" );
    }

    public EmailNotUniqueException(String email) {
        super("Email is not unique = " + email);
    }

}
