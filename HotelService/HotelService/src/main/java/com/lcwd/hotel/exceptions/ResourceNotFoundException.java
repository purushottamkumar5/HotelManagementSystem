package com.lcwd.hotel.exceptions;

import org.springframework.web.servlet.resource.ResourceTransformer;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException()
    {
        super("Resource not found");
    }
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
