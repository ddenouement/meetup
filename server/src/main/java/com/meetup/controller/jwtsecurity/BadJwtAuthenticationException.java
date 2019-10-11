package com.meetup.controller.jwtsecurity;

import org.springframework.security.core.AuthenticationException;

public class BadJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = -761503632186596342L;

    public BadJwtAuthenticationException(String e) {
        super(e);
    }
}
