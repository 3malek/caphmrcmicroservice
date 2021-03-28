package com.malik.urfan.microservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid postal code or date")  // 400
public class InvalidParametersException extends RuntimeException {

    public InvalidParametersException( String msg )
    {
        super( msg );
    }

}
